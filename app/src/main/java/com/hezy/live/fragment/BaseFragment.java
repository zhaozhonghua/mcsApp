package com.hezy.live.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.hezy.live.ApiClient;
import com.hezy.live.persistence.Preferences;

import java.lang.ref.WeakReference;

public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getName();

    protected SharedPreferences prefs;

    protected ApiClient client;

    protected String userId;

    protected String token;

    public FragmentHandler handler = new FragmentHandler(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        userId = Preferences.getUserId(prefs);
        token = Preferences.getToken(prefs);

        client = ApiClient.getInstance();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated");
        Activity activity = getActivity();
        if (activity != null) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
        refreshView();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");

    }

    public static class FragmentHandler extends Handler {
        WeakReference<BaseFragment> fragmentWeakReference;

        FragmentHandler(BaseFragment baseFragment) {
            fragmentWeakReference = new WeakReference<>(baseFragment);
        }

        @Override
        public void handleMessage(android.os.Message message) {
            BaseFragment baseFragment = fragmentWeakReference.get();
            switch (message.what) {
                case 0:
                    if (baseFragment != null) {
                        baseFragment.refreshView();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void refreshView() {
    }

}
