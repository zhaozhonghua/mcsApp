package com.hezy.live.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hezy.live.entity.Entity;

import java.util.ArrayList;

abstract class BaseListAdapter<T extends Entity> extends BaseAdapter {

    public BaseListAdapter(Context context) {
        super();
    }

    ArrayList<T> mArrayList = null;

    @Override
    public int getCount() {
        return isEmpty() ? 0 : mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return (mArrayList == null) ? true : mArrayList.isEmpty();
    }

    public void setData(ArrayList<T> arrayList) {
        mArrayList = arrayList;
        notifyDataSetChanged();
    }

}
