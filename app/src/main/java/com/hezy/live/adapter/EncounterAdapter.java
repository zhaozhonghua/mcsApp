package com.hezy.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hezy.live.R;
import com.hezy.live.entity.Encounter;
import com.hezy.live.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EncounterAdapter extends BaseListAdapter<Encounter> {

    private LayoutInflater mInflater;
    private Context mContext;

    public EncounterAdapter(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EncounterAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_coursera, null);
            holder = new EncounterAdapter.ViewHolder();

            holder.courseraImage = (ImageView) convertView.findViewById(R.id.coursera_image);
            holder.courseraNameText = (TextView) convertView.findViewById(R.id.coursera_name);
            holder.courseraStatusText = (TextView) convertView.findViewById(R.id.coursera_status);
            holder.courseraCountText = (TextView) convertView.findViewById(R.id.coursera_count);
            holder.courseraPriceText = (TextView) convertView.findViewById(R.id.coursera_price);
            holder.audienceCountText = (TextView) convertView.findViewById(R.id.audience_count);

            convertView.setTag(holder);
        } else {
            holder = (EncounterAdapter.ViewHolder) convertView.getTag();
        }

        Encounter encounter = (Encounter) getItem(position);
        holder.courseraNameText.setText(encounter.registerCode);
        Picasso.with(mContext).load(encounter.patient.head).transform(new RoundedTransformation(50, 0)).into(holder.courseraImage);
        // 0：待处理 1 正在处理 2：处理完成
        if (encounter.status == 0) {
            holder.courseraStatusText.setText("待处理");
        } else if(encounter.status == 1){
            holder.courseraStatusText.setText("处理中");
        }else{
            holder.courseraStatusText.setText("处理完成");
        }
        holder.courseraCountText.setText(encounter.registerCode + "1");
        holder.courseraPriceText.setText(encounter.registerCode + "2");
        holder.audienceCountText.setText(encounter.registerCode + "3");

        return convertView;
    }

    static class ViewHolder {
        TextView courseraNameText, courseraStatusText, courseraCountText, courseraPriceText, audienceCountText;
        ImageView courseraImage;
    }

    @Override
    public void setData(ArrayList<Encounter> list) {
        super.setData(list);
    }
}
