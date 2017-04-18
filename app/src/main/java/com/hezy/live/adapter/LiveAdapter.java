package com.hezy.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.entity.Lesson;

import java.util.ArrayList;

public class LiveAdapter extends BaseListAdapter<Lesson> {

    private LayoutInflater mInflater;

    public LiveAdapter(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_live, null);
            holder = new ViewHolder();
            holder.courseraNameText = (TextView) convertView.findViewById(R.id.coursera_name);
            holder.lessonNameText = (TextView) convertView.findViewById(R.id.lesson_name);
            holder.timeText = (TextView) convertView.findViewById(R.id.time);
            holder.statusText = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lesson lesson = (Lesson) getItem(position);
        holder.timeText.setText(lesson.getStartTime().substring(10, 16));
        holder.courseraNameText.setText(lesson.getCurrName());
        holder.lessonNameText.setText("第" + lesson.getSortNum() + "节 " + lesson.getLessonName());
        if (lesson.getCompletionStatus() == 1) {
            holder.statusText.setText("直播未开始");
        } else if (lesson.getCompletionStatus() == 2) {
            holder.statusText.setText("正在直播中");
        } else if (lesson.getCompletionStatus() == 3) {
            holder.statusText.setText("直播已结束");
        } else {

        }
        return convertView;
    }

    private static class ViewHolder {
        TextView courseraNameText, lessonNameText, timeText, statusText;
    }

    @Override
    public void setData(ArrayList<Lesson> list) {
        super.setData(list);
    }

}
