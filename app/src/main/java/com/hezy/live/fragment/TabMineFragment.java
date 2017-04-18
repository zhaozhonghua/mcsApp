package com.hezy.live.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.activity.LiveCreateActivity;
import com.hezy.live.activity.ProfileActivity;
import com.hezy.live.activity.PwdModifyActivity;
import com.hezy.live.activity.SignInActivity;
import com.hezy.live.persistence.Preferences;
import com.hezy.live.util.CircleTransform;
import com.hezy.live.util.FileUtil;
import com.hezy.live.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TabMineFragment extends BaseFragment {

    private static final String TAG = TabMineFragment.class.getSimpleName();

    private LinearLayout infoLayout;
    private ImageView avatarImage;

    private TextView nameText, signText, mobileText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        infoLayout = (LinearLayout) view.findViewById(R.id.info_layout);
        infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        avatarImage = (ImageView) view.findViewById(R.id.avatar);
        nameText = (TextView) view.findViewById(R.id.name);
        signText = (TextView) view.findViewById(R.id.desc);
        mobileText = (TextView) view.findViewById(R.id.mobile);

        view.findViewById(R.id.change_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PwdModifyActivity.class));
            }
        });

        view.findViewById(R.id.sign_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("确定退出帐号？");
                builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Preferences.clear(prefs);
                        startActivity(new Intent(getActivity(), SignInActivity.class));
                        getActivity().finish();
                    }
                });
                builder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

        return view;
    }

    private void assign() {
        if (!TextUtils.isEmpty(Preferences.getUserAvatar(prefs))) {
            Picasso.with(getActivity()).load(Preferences.getImageDomain(prefs) + Preferences.getUserAvatar(prefs)).placeholder(R.drawable.ic_avatar_default).error(R.drawable.ic_avatar_default).transform(new CircleTransform()).into(avatarImage);
            applyBlur();
        }
        nameText.setText(Preferences.getUserName(prefs));
        if ("男".equals(Preferences.getUserGender(prefs))) {
            nameText.setCompoundDrawablePadding(16);
            nameText.setCompoundDrawablesWithIntrinsicBounds(null, null, getActivity().getResources().getDrawable(R.drawable.ic_man), null);
        } else {
            nameText.setCompoundDrawablePadding(16);
            nameText.setCompoundDrawablesWithIntrinsicBounds(null, null, getActivity().getResources().getDrawable(R.drawable.ic_woman), null);
        }
        mobileText.setText("已绑定手机   " + Preferences.getUserMobile(prefs));
        signText.setText(Preferences.getUserSign(prefs));

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        if (activity != null) {
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        assign();
    }

    @Override
    public void refreshView() {
        super.refreshView();
    }

    private void applyBlur() {
        new ImageTask().execute();
    }

    class ImageTask extends AsyncTask {

        Bitmap bitmap;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                bitmap = Picasso.with(getActivity()).load("http://imagetest.i.haierzhongyou.com/" + Preferences.getUserAvatar(prefs)).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (bitmap != null)
                infoLayout.setBackground(new BitmapDrawable(ImageUtil.blurBitmap(getActivity(), bitmap, 15f)));
        }
    }

}