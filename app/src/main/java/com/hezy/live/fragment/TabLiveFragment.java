package com.hezy.live.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hezy.live.BaseApplication;
import com.hezy.live.BaseException;
import com.hezy.live.R;
import com.hezy.live.activity.LiveCreateActivity;
import com.hezy.live.activity.SignInActivity;
import com.hezy.live.adapter.LiveAdapter;
import com.hezy.live.callback.AgroaCallback;
import com.hezy.live.callback.LessonsCallback;
import com.hezy.live.entity.Agroa;
import com.hezy.live.entity.Lesson;
import com.hezy.live.entity.Lessons;
import com.hezy.live.persistence.Preferences;
import com.hezy.live.util.UIDUtil;
import com.hezy.live.widget.FixedListView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import io.agora.openlive.ui.MainActivity;

public class TabLiveFragment extends BaseFragment {

    private static final String TAG = TabLiveFragment.class.getSimpleName();

    private TextView timeText, courseraNameText, lessonNameText;
    private LinearLayout livingInfoLayout;
    private Button toLiveButton;
    private MaterialCalendarView calendarView;
    private FixedListView listView;
    private LiveAdapter liveAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        timeText = (TextView) view.findViewById(R.id.timer);
        courseraNameText = (TextView) view.findViewById(R.id.coursera_name);
        lessonNameText = (TextView) view.findViewById(R.id.lesson_name);
        livingInfoLayout = (LinearLayout) view.findViewById(R.id.living_info);
        toLiveButton = (Button) view.findViewById(R.id.to_live);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.live_calender);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay calendarDay, boolean selected) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                try {
                    Date date = sdf.parse(calendarDay.getYear() + "-" + (calendarDay.getMonth() + 1) + "-" + calendarDay.getDay());
                    long timestamp = date.getTime();
                    liveLessons(timestamp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        calendarView.setDateSelected(new Date(), true);

        listView = (FixedListView) view.findViewById(R.id.list_view);
        liveAdapter = new LiveAdapter(getActivity());
        listView.setAdapter(liveAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        if (activity != null) {
            liveLessons(System.currentTimeMillis());
        }
    }

    private void liveLessons(long timeMillis) {
        client.liveLessons(timeMillis, Preferences.getUserId(prefs), Preferences.getToken(prefs), lessonsCallback);
    }

    private LessonsCallback lessonsCallback = new LessonsCallback() {
        @Override
        protected void onSuccess(Lessons lessons) {
            Log.d("lesson", lessons.toString());
            liveAdapter.setData(lessons.getPageData());
            if (lessons.getPageData() != null && lessons.getPageData().size() > 0) {
                setHeaderLiveLesson(getCurrentLiveLesson(lessons.getPageData()));
            } else {
                timeText.setVisibility(View.GONE);
                livingInfoLayout.setVisibility(View.GONE);
            }
        }

        @Override
        protected void onFailure(BaseException exception) {
            if (exception.getStatusCode() == 40003) {
                Preferences.clear(prefs);
                Toast.makeText(getActivity(), "登录已过期，请重新登录！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), SignInActivity.class));
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), exception.getStatusCode() + exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void refreshView() {

    }

    private void setHeaderLiveLesson(Lesson lesson) {
        if (lesson != null) {
            timeText.setVisibility(View.VISIBLE);
            livingInfoLayout.setVisibility(View.VISIBLE);
            courseraNameText.setText(lesson.getCurrName());
            lessonNameText.setText(lesson.getLessonName());
            timeText.setText(lesson.getStartTime());
            toLiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uid = UIDUtil.generatorUID(userId);
                    HashMap<String, String> params = new HashMap<>();
                    params.put("channel", lesson.getId());
                    params.put("uid", uid);
                    client.agora(params, Preferences.getToken(prefs), agroaCallback(lesson, uid));
                }
            });
        } else {
            timeText.setVisibility(View.GONE);
            livingInfoLayout.setVisibility(View.GONE);
        }
    }

    private AgroaCallback agroaCallback(final Lesson lesson, final String uid) {
        return new AgroaCallback() {
            @Override
            protected void onSuccess(Agroa agroa) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("lesson", lesson);
                intent.putExtra("agroa", agroa);
                intent.putExtra("user_id", uid);
                ((BaseApplication) getActivity().getApplication()).setAppId(agroa.getAppID());
                startActivity(intent);
            }

            @Override
            protected void onFailure(BaseException exception) {
                Toast.makeText(getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private Lesson getCurrentLiveLesson(ArrayList<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getCompletionStatus() == 2) {
                return lesson;
            }
        }
        return null;
    }

}
