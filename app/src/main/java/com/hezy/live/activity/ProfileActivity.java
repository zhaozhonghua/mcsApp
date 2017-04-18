package com.hezy.live.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.callback.QiNiuTokenCallback;
import com.hezy.live.callback.RespStatusCallback;
import com.hezy.live.callback.UserCallback;
import com.hezy.live.entity.QiNiuToken;
import com.hezy.live.entity.RespStatus;
import com.hezy.live.entity.User;
import com.hezy.live.persistence.Preferences;
import com.hezy.live.util.CircleTransform;
import com.hezy.live.util.FileUtil;
import com.hezy.live.util.Md5Util;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProfileActivity extends BaseActivity {

    private ImageView avatarImage;
    private TextView nameText, genderText, signText;

    private File avatarFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initToolBar();

        init();

    }

    private void init() {
        avatarImage = (ImageView) findViewById(R.id.avatar);
        Picasso.with(getApplicationContext()).load(Preferences.getImageDomain(prefs) + Preferences.getUserAvatar(prefs)).placeholder(R.drawable.ic_avatar_default).error(R.drawable.ic_avatar_default).transform(new CircleTransform()).into(avatarImage);
        avatarImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, AvatarActivity.class));
            }
        });

        nameText = (TextView) findViewById(R.id.name);
        nameText.setText(Preferences.getUserName(prefs));

        genderText = (TextView) findViewById(R.id.gender);
        genderText.setText(Preferences.getUserGender(prefs));

        signText = (TextView) findViewById(R.id.sign);
        signText.setText(Preferences.getUserSign(prefs));
    }

    public void modifyAvatarImage(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(getResources().getStringArray(R.array.string_array_image_seletor), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    String uniqueName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date(System
                            .currentTimeMillis())) + ".jpg";
                    avatarFile = FileUtil.getDiskCacheDir(ProfileActivity.this, "images", uniqueName);
                    if (avatarFile != null) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(avatarFile));
                        startActivityForResult(intent, 10);
                    }
                } else if (i == 1) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 11);
                }
            }
        });
        builder.create().show();
    }

    public void modifyName(View view) {
        startActivityForResult(new Intent(this, ProfileModifyActivity.class).putExtra("content", "name"), 12);
    }

    public void modifySign(View view) {
        startActivityForResult(new Intent(this, ProfileModifyActivity.class).putExtra("content", "sign"), 13);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 10) {
                if (avatarFile != null) {

                } else {
                    Toast.makeText(ProfileActivity.this, "camera error", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == 11 && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String filePath = cursor.getString(column_index);
                cursor.close();
                avatarFile = new File(filePath);
                client.qiniuToken("image", Preferences.getToken(prefs), qiNiuTokenCallback);
            } else if (requestCode == 12) {
                nameText.setText(Preferences.getUserName(prefs));
            } else if (requestCode == 13) {
                signText.setText(Preferences.getUserSign(prefs));
            }
        }
    }


    private QiNiuTokenCallback qiNiuTokenCallback = new QiNiuTokenCallback() {
        @Override
        protected void onSuccess(QiNiuToken qiNiuToken) {
            Configuration config = new Configuration.Builder().zone(Zone.zone0).build();
            UploadManager uploadManager = new UploadManager(config);
            uploadManager.put(
                    avatarFile,
                    "dz/image/teacher/avatar/" + UUID.randomUUID().toString().replace("-", "") + avatarFile.getName().substring(avatarFile.getName().lastIndexOf(".")),
                    qiNiuToken.getToken(),
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info, JSONObject res) {
                            try {
                                JSONObject jsonParam = new JSONObject();
                                jsonParam.put("pictrue", key);
                                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParam.toString());
                                client.profileModify(requestBody, Preferences.getUserId(prefs), Preferences.getToken(prefs), userCallback);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    null);
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(getApplicationContext(), exception.getStatusCode() + "" + exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private UserCallback userCallback = new UserCallback() {
        @Override
        protected void onSuccess(User user) {
            Preferences.setUserAvatar(prefs, user.head);
            Picasso.with(getApplicationContext()).load(Preferences.getImageDomain(prefs) + Preferences.getUserAvatar(prefs)).placeholder(R.drawable.ic_avatar_default).error(R.drawable.ic_avatar_default).transform(new CircleTransform()).into(avatarImage);
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(getApplicationContext(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

}
