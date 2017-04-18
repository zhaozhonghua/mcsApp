package com.hezy.live.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.activity.CourseraActivity;
import com.hezy.live.activity.LiveCreateActivity;
import com.hezy.live.adapter.EncounterAdapter;
import com.hezy.live.callback.EncountersCallback;
import com.hezy.live.entity.Encounter;
import com.hezy.live.entity.Encounters;
import com.hezy.live.persistence.Preferences;

public class TabCourseraFragment extends BaseFragment {

    private static final String TAG = TabCourseraFragment.class.getSimpleName();

    private ListView listView;
    private EncounterAdapter encounterAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);
        encounterAdapter = new EncounterAdapter(getActivity());
        listView.setAdapter(encounterAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Encounter encounter = (Encounter) adapterView.getItemAtPosition(i);
                Log.d("tag", encounter.registerCode);
                startActivity(new Intent(getActivity(), CourseraActivity.class).putExtra("encounter", encounter));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        if (activity != null) {
            encounters();
        }
    }

    private void encounters() {
        client.encounters(Preferences.getUserType(prefs), Preferences.getToken(prefs), encountersCallback);
    }

    private EncountersCallback encountersCallback = new EncountersCallback() {
        @Override
        protected void onSuccess(Encounters encounters) {
            if (encounters.getData() != null && !encounters.getData().isEmpty()) {
                Log.d(TAG, "EncountersCallback onSuccess");
                encounterAdapter.setData(encounters.getData());
            } else {
                Toast.makeText(getActivity(), "暂无病例", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onFailure(BaseException exception) {
            Toast.makeText(getActivity(), exception.getStatusCode() + exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_coursera_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_coursera_add:
                startActivity(new Intent(getActivity(), LiveCreateActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
