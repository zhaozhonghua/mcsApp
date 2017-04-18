package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whatisjava on 15/11/11.
 */
public class Agroa implements Parcelable, Entity {

    private String isTest;

    private String channelKey;

    private String appID;

    private String recordingKey;

    public String getIsTest() {
        return isTest;
    }

    public void setIsTest(String isTest) {
        this.isTest = isTest;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getRecordingKey() {
        return recordingKey;
    }

    public void setRecordingKey(String recordingKey) {
        this.recordingKey = recordingKey;
    }

    @Override
    public String toString() {
        return "Agroa{" +
                "isTest='" + isTest + '\'' +
                ", channelKey='" + channelKey + '\'' +
                ", appID='" + appID + '\'' +
                ", recordingKey='" + recordingKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agroa agroa = (Agroa) o;

        if (isTest != null ? !isTest.equals(agroa.isTest) : agroa.isTest != null) return false;
        if (channelKey != null ? !channelKey.equals(agroa.channelKey) : agroa.channelKey != null)
            return false;
        if (appID != null ? !appID.equals(agroa.appID) : agroa.appID != null) return false;
        return recordingKey != null ? recordingKey.equals(agroa.recordingKey) : agroa.recordingKey == null;

    }

    @Override
    public int hashCode() {
        int result = isTest != null ? isTest.hashCode() : 0;
        result = 31 * result + (channelKey != null ? channelKey.hashCode() : 0);
        result = 31 * result + (appID != null ? appID.hashCode() : 0);
        result = 31 * result + (recordingKey != null ? recordingKey.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isTest);
        dest.writeString(this.channelKey);
        dest.writeString(this.appID);
        dest.writeString(this.recordingKey);
    }

    public Agroa() {
    }

    protected Agroa(Parcel in) {
        this.isTest = in.readString();
        this.channelKey = in.readString();
        this.appID = in.readString();
        this.recordingKey = in.readString();
    }

    public static final Creator<Agroa> CREATOR = new Creator<Agroa>() {
        @Override
        public Agroa createFromParcel(Parcel source) {
            return new Agroa(source);
        }

        @Override
        public Agroa[] newArray(int size) {
            return new Agroa[size];
        }
    };
}
