package com.hezy.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.entity.Coursera;
import com.hezy.live.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseraAdapter extends BaseListAdapter<Coursera> {

    private LayoutInflater mInflater;
    private Context mContext;

    public CourseraAdapter(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_coursera, null);
            holder = new ViewHolder();
            holder.courseraImage = (ImageView) convertView.findViewById(R.id.coursera_image);
            holder.courseraNameText = (TextView) convertView.findViewById(R.id.coursera_name);
            holder.courseraStatusText = (TextView) convertView.findViewById(R.id.coursera_status);
            holder.courseraCountText = (TextView) convertView.findViewById(R.id.coursera_count);
            holder.courseraPriceText = (TextView) convertView.findViewById(R.id.coursera_price);
            holder.audienceCountText = (TextView) convertView.findViewById(R.id.audience_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Coursera coursera = (Coursera) getItem(position);
        holder.courseraNameText.setText(coursera.getCurrName());
        Picasso.with(mContext).load(coursera.getCurrImg()).transform(new RoundedTransformation(50, 0)).into(holder.courseraImage);
        if ("0".equals(coursera.getDelFlag())) {
            holder.courseraStatusText.setText("启用中");
        } else if ("1".equals(coursera.getDelFlag())) {
            holder.courseraStatusText.setText("已删除");
        } else if ("2".equals(coursera.getDelFlag())) {
            holder.courseraStatusText.setText("已禁用");
        } else {

        }
        holder.courseraCountText.setText(coursera.getTotalLessons() + "个课时");
        holder.courseraPriceText.setText(coursera.getLessonPrice() == 0 ? "免费" : coursera.getLessonPrice() + "元");

        return convertView;
    }

    static class ViewHolder {
        TextView courseraNameText, courseraStatusText, courseraCountText, courseraPriceText, audienceCountText;
        ImageView courseraImage;
    }

    @Override
    public void setData(ArrayList<Coursera> list) {
        super.setData(list);
    }

}
