package com.hezy.live.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.hezy.live.R;
import com.hezy.live.persistence.Preferences;
import com.squareup.picasso.Picasso;

public class AvatarActivity extends BaseActivity {

    private ImageView avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_preview);
        initToolBar();

        init();
    }

    private void init() {
        avatarImage = (ImageView) findViewById(R.id.avatar);
        Picasso.with(getApplicationContext()).load(Preferences.getImageDomain(prefs) + Preferences.getUserAvatar(prefs)).placeholder(R.drawable.ic_avatar_default).error(R.drawable.ic_avatar_default).into(avatarImage);
    }

}
