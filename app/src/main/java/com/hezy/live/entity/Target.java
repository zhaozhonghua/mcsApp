package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whatisjava on 15/11/11.
 */
public class Target implements Parcelable, Entity {

    private String id;

    private String title;

    private String createDate;

    private String updateDate;

    private int target;

    private int lessonPrice;

    private int sortNum;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(int lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Target target1 = (Target) o;

        if (target != target1.target) return false;
        if (lessonPrice != target1.lessonPrice) return false;
        if (sortNum != target1.sortNum) return false;
        if (id != null ? !id.equals(target1.id) : target1.id != null) return false;
        if (title != null ? !title.equals(target1.title) : target1.title != null) return false;
        if (createDate != null ? !createDate.equals(target1.createDate) : target1.createDate != null)
            return false;
        if (updateDate != null ? !updateDate.equals(target1.updateDate) : target1.updateDate != null)
            return false;
        return delFlag != null ? delFlag.equals(target1.delFlag) : target1.delFlag == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + target;
        result = 31 * result + lessonPrice;
        result = 31 * result + sortNum;
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Target{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", target=" + target +
                ", lessonPrice=" + lessonPrice +
                ", sortNum=" + sortNum +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.createDate);
        dest.writeString(this.updateDate);
        dest.writeInt(this.target);
        dest.writeInt(this.lessonPrice);
        dest.writeInt(this.sortNum);
        dest.writeString(this.delFlag);
    }

    public Target() {
    }

    protected Target(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.createDate = in.readString();
        this.updateDate = in.readString();
        this.target = in.readInt();
        this.lessonPrice = in.readInt();
        this.sortNum = in.readInt();
        this.delFlag = in.readString();
    }

    public static final Creator<Target> CREATOR = new Creator<Target>() {
        @Override
        public Target createFromParcel(Parcel source) {
            return new Target(source);
        }

        @Override
        public Target[] newArray(int size) {
            return new Target[size];
        }
    };
}
