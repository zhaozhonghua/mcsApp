package com.hezy.live.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.fragment.TabCourseraFragment;
import com.hezy.live.fragment.TabLiveFragment;
import com.hezy.live.fragment.TabMineFragment;
import com.hezy.live.fragment.TabPatientRegisterFragment;

public class MainTabActivity extends BaseActivity {

    private static final String tag = MainTabActivity.class.getSimpleName();

    private FragmentManager manager;

    private FragmentTabHost mTabHost;

    private Class<?>[] fragmentArray = {TabPatientRegisterFragment.class, TabCourseraFragment.class, TabMineFragment.class};

    private int imageIds[] = {R.drawable.tab_live_selector, R.drawable.tab_coursera_selector, R.drawable.tab_mine_selector};

    private int tabLabels[] = {R.string.live_calendar, R.string.lesson_manager, R.string.mine_info};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.live_calendar));
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();

        init();
    }

    private void init() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        mTabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        for (int i = 0; i < fragmentArray.length; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(getResources().getString(tabLabels[i])).setIndicator(getIndicator(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }

        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                View decorView = getWindow().getDecorView();
                if (tabId.equals(getResources().getString(R.string.live_calendar))) {
                    toolbar.setTitle(getResources().getString(R.string.live_calendar));
                    toolbar.setVisibility(View.VISIBLE);
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else if (tabId.equals(getResources().getString(R.string.lesson_manager))) {
                    toolbar.setTitle(getResources().getString(R.string.lesson_manager));
                    toolbar.setVisibility(View.VISIBLE);
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else if (tabId.equals(getResources().getString(R.string.mine_info))) {
                    toolbar.setTitle(getResources().getString(R.string.mine_info));
                    toolbar.setVisibility(View.GONE);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {

                }
            }
        });
    }

    private View getIndicator(int index) {
        TextView textView = (TextView) View.inflate(this, R.layout.indicator_view, null);
        textView.setText(tabLabels[index]);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, imageIds[index], 0, 0);
        return textView;
    }

}
