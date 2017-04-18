package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by whatisjava on 15/11/11.
 */
public class Targets implements Parcelable, Entity {

    private ArrayList<Target> data;

    public ArrayList<Target> getData() {
        return data;
    }

    public void setData(ArrayList<Target> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Targets targets = (Targets) o;

        return data != null ? data.equals(targets.data) : targets.data == null;

    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Targets{" +
                "data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public Targets() {
    }

    protected Targets(Parcel in) {
        this.data = in.createTypedArrayList(Target.CREATOR);
    }

    public static final Creator<Targets> CREATOR = new Creator<Targets>() {
        @Override
        public Targets createFromParcel(Parcel source) {
            return new Targets(source);
        }

        @Override
        public Targets[] newArray(int size) {
            return new Targets[size];
        }
    };
}
