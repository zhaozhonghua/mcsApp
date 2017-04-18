package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whatisjava on 15/11/11.
 */
public class Coursera implements Parcelable, Entity {

//            "updateDate": "2016-11-17 19:22:14",
//            "videoLessonCnt": 1,
//            "displayStatus": 1,
//            "delFlag": "0",
//            "totalLessons": 12,
//            "currThemeTarget": 0,
//            "lessonPrice": 12,
//            "tips": "课程已更新至第3课时",
//            "capacity": 12,
//            "buylessonFlag": "0",
//            "currImg": "http://imagetest.i.haierzhongyou.com/dz/msjt/6c69333a-3473-4829-a1ed-7cd816e1a8d9.jpg",
//            "id": "983ee09d2a824e6a94073e351e1053ca",
//            "currName": "谁看见的疯狂涉及到",
//            "createDate": "2016-11-15 15:24:00",
//            "subscriptionCnt": 2,
//            "teacherName": "小明",
//            "currThemeTitle": "测试分类",
//            "teacherPicture": "http://imagetest.i.haierzhongyou.com/dz/sysuser/picture/136a5fdd-5235-4d29-b84d-687d8747d013.png",
//            "liveLessonCnt": 2,
//            "teacherId": "21",
//            "currThemeId": "1",
//            "currPrice": 12,
//            "endTime": "2016-11-20 00:00:00",
//            "teacherTitle": "我是管理员，不是老师，超级管理员",
//            "currAbstract": "看见可适当",
//            "usageCount": 1,
//            "validDays": 12

    private String id;

    private String currName;

    private String currImg;

    private int totalLessons;

    private int lessonPrice;

    private int usageCount;

    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public String getCurrImg() {
        return currImg;
    }

    public void setCurrImg(String currImg) {
        this.currImg = currImg;
    }

    public int getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }

    public int getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(int lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
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

        Coursera coursera = (Coursera) o;

        if (totalLessons != coursera.totalLessons) return false;
        if (lessonPrice != coursera.lessonPrice) return false;
        if (usageCount != coursera.usageCount) return false;
        if (id != null ? !id.equals(coursera.id) : coursera.id != null) return false;
        if (currName != null ? !currName.equals(coursera.currName) : coursera.currName != null)
            return false;
        if (currImg != null ? !currImg.equals(coursera.currImg) : coursera.currImg != null)
            return false;
        return delFlag != null ? delFlag.equals(coursera.delFlag) : coursera.delFlag == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currName != null ? currName.hashCode() : 0);
        result = 31 * result + (currImg != null ? currImg.hashCode() : 0);
        result = 31 * result + totalLessons;
        result = 31 * result + lessonPrice;
        result = 31 * result + usageCount;
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Coursera{" +
                "id='" + id + '\'' +
                ", currName='" + currName + '\'' +
                ", currImg='" + currImg + '\'' +
                ", totalLessons=" + totalLessons +
                ", lessonPrice=" + lessonPrice +
                ", usageCount=" + usageCount +
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
        dest.writeString(this.currName);
        dest.writeString(this.currImg);
        dest.writeInt(this.totalLessons);
        dest.writeInt(this.lessonPrice);
        dest.writeInt(this.usageCount);
        dest.writeString(this.delFlag);
    }

    public Coursera() {
    }

    protected Coursera(Parcel in) {
        this.id = in.readString();
        this.currName = in.readString();
        this.currImg = in.readString();
        this.totalLessons = in.readInt();
        this.lessonPrice = in.readInt();
        this.usageCount = in.readInt();
        this.delFlag = in.readString();
    }

    public static final Creator<Coursera> CREATOR = new Creator<Coursera>() {
        @Override
        public Coursera createFromParcel(Parcel source) {
            return new Coursera(source);
        }

        @Override
        public Coursera[] newArray(int size) {
            return new Coursera[size];
        }
    };
}
