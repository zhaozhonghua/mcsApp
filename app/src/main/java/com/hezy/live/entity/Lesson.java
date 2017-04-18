package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whatisjava on 15/11/11.
 */
public class Lesson implements Parcelable, Entity {

//            "agoraSecretid": "",
//            "lessonImg": "lesson.jpg",
//            "lessonAbstract": "课时介绍",
//            "type": 0,
//            "replayUrl": "replay/abc/fff",
//            "curriculumId": "b42cdbe2f3cf453ab8317a831d9069e7",
//            "delFlag": "0",
//            "lessonName": "测试课时1477278210732",
//            "conferenceKey": "abcd",
//            "agoraAppid": "16e03118e5e94893a3551b3b03cbba89",
//            "conferencePasswd": "room第一",
//            "startTime": "2016-10-24 11:03:30",
//            "endTime": "2016-10-24 11:03:30",
//            "sortNum" : 6,
//            "currName": "课程名称",
//            "id": "841f365aabb5474f81e675189dd6dd05"

//            "id": "8be5d4b7a0fe4dde97e6ce779e468f76",
//            "delFlag": "0",
//            "createDate": "2016-12-16 11:20:23",
//            "updateDate": "2016-12-16 11:20:23",
//            "lessonName": "test",
//            "lessonImg": "http://imagetest.i.haierzhongyou.com/dz/msjt/curriculum/image/ef77a85db1c94e3c955b709620800e70.jpg",
//            "startTime": "2016-12-16 11:20:23",
//            "endTime": "2016-12-16 11:25:23",
//            "curriculumId": "67f5fc48f56f4c6daa2271b20fe5e72d",
//            "type": 0,
//            "freeAudition": "1",
//            "sortNum": 1,
//            "currName": "test",
//            "completionStatus": 2,
//            "usageCount": 0

    private String id;

    private String currName;

    private String lessonName;

    private int completionStatus;  // 0 未知、 1 未开始、 2 直播中、 3 已结束

    private int type;

    private int sortNum;

    private String delFlag;  // 0 启用、 1 删除、 2 已禁用

    private String lessonImg;

    private String startTime;

    private String endTime;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(int completionStatus) {
        this.completionStatus = completionStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getLessonImg() {
        return lessonImg;
    }

    public void setLessonImg(String lessonImg) {
        this.lessonImg = lessonImg;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (completionStatus != lesson.completionStatus) return false;
        if (type != lesson.type) return false;
        if (sortNum != lesson.sortNum) return false;
        if (id != null ? !id.equals(lesson.id) : lesson.id != null) return false;
        if (currName != null ? !currName.equals(lesson.currName) : lesson.currName != null)
            return false;
        if (lessonName != null ? !lessonName.equals(lesson.lessonName) : lesson.lessonName != null)
            return false;
        if (startTime != null ? !startTime.equals(lesson.startTime) : lesson.startTime != null)
            return false;
        if (delFlag != null ? !delFlag.equals(lesson.delFlag) : lesson.delFlag != null)
            return false;
        if (lessonImg != null ? !lessonImg.equals(lesson.lessonImg) : lesson.lessonImg != null)
            return false;
        if (startTime != null ? !startTime.equals(lesson.startTime) : lesson.startTime != null)
            return false;
        return endTime != null ? endTime.equals(lesson.endTime) : lesson.endTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currName != null ? currName.hashCode() : 0);
        result = 31 * result + (lessonName != null ? lessonName.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + completionStatus;
        result = 31 * result + type;
        result = 31 * result + sortNum;
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (lessonImg != null ? lessonImg.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", currName='" + currName + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", completionStatus=" + completionStatus +
                ", type=" + type +
                ", sortNum=" + sortNum +
                ", delFlag='" + delFlag + '\'' +
                ", lessonImg='" + lessonImg + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
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
        dest.writeString(this.lessonName);
        dest.writeString(this.startTime);
        dest.writeInt(this.completionStatus);
        dest.writeInt(this.type);
        dest.writeInt(this.sortNum);
        dest.writeString(this.delFlag);
        dest.writeString(this.lessonImg);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
    }

    public Lesson() {
    }

    protected Lesson(Parcel in) {
        this.id = in.readString();
        this.currName = in.readString();
        this.lessonName = in.readString();
        this.startTime = in.readString();
        this.completionStatus = in.readInt();
        this.type = in.readInt();
        this.sortNum = in.readInt();
        this.delFlag = in.readString();
        this.lessonImg = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel source) {
            return new Lesson(source);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };
}
