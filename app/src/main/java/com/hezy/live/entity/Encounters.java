package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Encounters implements Parcelable, Entity {

    private ArrayList<Encounter> data;

    public ArrayList<Encounter> getData() {
        return data;
    }

    public void setData(ArrayList<Encounter> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Encounters encounters = (Encounters) o;
        return data != null ? data.equals(encounters.data) : encounters.data == null;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public Encounters() {
    }

    protected Encounters(Parcel in) {
        this.data = in.createTypedArrayList(Encounter.CREATOR);
    }

    public static final Creator<Encounters> CREATOR = new Creator<Encounters>() {
        @Override
        public Encounters createFromParcel(Parcel source) {
            return new Encounters(source);
        }

        @Override
        public Encounters[] newArray(int size) {
            return new Encounters[size];
        }
    };
}
