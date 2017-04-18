package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Lessons implements Parcelable, Entity {

    private int pageNo;

    private int totalPage;

    private int pageSize;

    private int totalCount;

    private ArrayList<Lesson> pageData;

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

    public ArrayList<Lesson> getPageData() {
        return pageData;
    }

    public void setPageData(ArrayList<Lesson> pageData) {
        this.pageData = pageData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pageData == null) ? 0 : pageData.hashCode());
        result = prime * result + pageNo;
        result = prime * result + pageSize;
        result = prime * result + totalCount;
        result = prime * result + totalPage;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lessons other = (Lessons) obj;
        if (pageData == null) {
            if (other.pageData != null)
                return false;
        } else if (!pageData.equals(other.pageData))
            return false;
        if (pageNo != other.pageNo)
            return false;
        if (pageSize != other.pageSize)
            return false;
        if (totalCount != other.totalCount)
            return false;
        if (totalPage != other.totalPage)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lessons [pageNo=" + pageNo + ", totalPage=" + totalPage + ", pageSize=" +
                pageSize + ", totalCount=" + totalCount + ", pageData=" + pageData + "]";
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

    public Lessons() {
    }

    protected Lessons(Parcel in) {
        this.pageNo = in.readInt();
        this.totalPage = in.readInt();
        this.pageSize = in.readInt();
        this.totalCount = in.readInt();
        this.pageData = in.createTypedArrayList(Lesson.CREATOR);
    }

    public static final Creator<Lessons> CREATOR = new Creator<Lessons>() {
        @Override
        public Lessons createFromParcel(Parcel source) {
            return new Lessons(source);
        }

        @Override
        public Lessons[] newArray(int size) {
            return new Lessons[size];
        }
    };
}
