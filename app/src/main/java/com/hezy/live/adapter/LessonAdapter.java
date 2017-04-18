package com.hezy.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.entity.Coursera;
import com.hezy.live.entity.Lesson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LessonAdapter extends BaseListAdapter<Lesson> {

    private LayoutInflater mInflater;
    private Context mContext;

    public LessonAdapter(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lesson, null);
            holder = new ViewHolder();
            holder.lessonCoverImage = (ImageView) convertView.findViewById(R.id.lesson_cover);
            holder.lessonNameText = (TextView) convertView.findViewById(R.id.lesson_name);
            holder.durationText = (TextView) convertView.findViewById(R.id.duration);
            holder.lessonTypeText = (TextView) convertView.findViewById(R.id.type);
            holder.timeText = (TextView) convertView.findViewById(R.id.time);
            holder.statusText = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lesson lesson = (Lesson) getItem(position);

        Picasso.with(mContext).load(lesson.getLessonImg()).into(holder.lessonCoverImage);

        holder.lessonNameText.setText("第" + lesson.getSortNum() + "节 " + lesson.getLessonName());

//        holder.durationText.setText();

        if (lesson.getType() == 0) {
            holder.lessonTypeText.setText("直播");
        } else {
            holder.lessonTypeText.setText("视频");
        }

        holder.timeText.setText(lesson.getStartTime());

        if ("0".equals(lesson.getDelFlag())) {
            holder.statusText.setText("启用中");
        } else if ("1".equals(lesson.getDelFlag())) {
            holder.statusText.setText("已删除");
        } else if ("2".equals(lesson.getDelFlag())) {
            holder.statusText.setText("已禁用");
        } else {

        }

        return convertView;
    }

    private static class ViewHolder {
        TextView lessonNameText, lessonTypeText, timeText, statusText, durationText;
        ImageView lessonCoverImage;
    }

    @Override
    public void setData(ArrayList<Lesson> list) {
        super.setData(list);
    }

}
