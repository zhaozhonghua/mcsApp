package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by whatisjava on 15/11/11.
 */
public class Courseras implements Parcelable, Entity {

    private int pageNo;

    private int totalPage;

    private int pageSize;

    private int totalCount;

    private ArrayList<Coursera> pageData;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<Coursera> getPageData() {
        return pageData;
    }

    public void setPageData(ArrayList<Coursera> pageData) {
        this.pageData = pageData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Courseras courseras = (Courseras) o;

        if (pageNo != courseras.pageNo) return false;
        if (totalPage != courseras.totalPage) return false;
        if (pageSize != courseras.pageSize) return false;
        if (totalCount != courseras.totalCount) return false;
        return pageData != null ? pageData.equals(courseras.pageData) : courseras.pageData == null;

    }

    @Override
    public int hashCode() {
        int result = pageNo;
        result = 31 * result + totalPage;
        result = 31 * result + pageSize;
        result = 31 * result + totalCount;
        result = 31 * result + (pageData != null ? pageData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Courseras{" +
                "pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", pageData=" + pageData +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pageNo);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.pageSize);
        dest.writeInt(this.totalCount);
        dest.writeTypedList(this.pageData);
    }

    public Courseras() {
    }

    protected Courseras(Parcel in) {
        this.pageNo = in.readInt();
        this.totalPage = in.readInt();
        this.pageSize = in.readInt();
        this.totalCount = in.readInt();
        this.pageData = in.createTypedArrayList(Coursera.CREATOR);
    }

    public static final Creator<Courseras> CREATOR = new Creator<Courseras>() {
        @Override
        public Courseras createFromParcel(Parcel source) {
            return new Courseras(source);
        }

        @Override
        public Courseras[] newArray(int size) {
            return new Courseras[size];
        }
    };
}
