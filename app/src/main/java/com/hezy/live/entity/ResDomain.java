package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by whatisjava on 15/11/11.
 */
public class ResDomain implements Parcelable, Entity {

    private String imgUrl;

    private String videoUrl;

    private String downloadUrl;

    private String cooperationUrl;

    @Override
    public String toString() {
        return "ResDomain{" +
                "imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", cooperationUrl='" + cooperationUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResDomain resDomain = (ResDomain) o;

        if (imgUrl != null ? !imgUrl.equals(resDomain.imgUrl) : resDomain.imgUrl != null)
            return false;
        if (videoUrl != null ? !videoUrl.equals(resDomain.videoUrl) : resDomain.videoUrl != null)
            return false;
        if (downloadUrl != null ? !downloadUrl.equals(resDomain.downloadUrl) : resDomain.downloadUrl != null)
            return false;
        return cooperationUrl != null ? cooperationUrl.equals(resDomain.cooperationUrl) : resDomain.cooperationUrl == null;

    }

    @Override
    public int hashCode() {
        int result = imgUrl != null ? imgUrl.hashCode() : 0;
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
        result = 31 * result + (cooperationUrl != null ? cooperationUrl.hashCode() : 0);
        return result;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getCooperationUrl() {
        return cooperationUrl;
    }

    public void setCooperationUrl(String cooperationUrl) {
        this.cooperationUrl = cooperationUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imgUrl);
        dest.writeString(this.videoUrl);
        dest.writeString(this.downloadUrl);
        dest.writeString(this.cooperationUrl);
    }

    public ResDomain() {
    }

    protected ResDomain(Parcel in) {
        this.imgUrl = in.readString();
        this.videoUrl = in.readString();
        this.downloadUrl = in.readString();
        this.cooperationUrl = in.readString();
    }

    public static final Creator<ResDomain> CREATOR = new Creator<ResDomain>() {
        @Override
        public ResDomain createFromParcel(Parcel source) {
            return new ResDomain(source);
        }

        @Override
        public ResDomain[] newArray(int size) {
            return new ResDomain[size];
        }
    };
}
