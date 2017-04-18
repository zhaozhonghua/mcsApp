package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable, Entity {

    public String token;

    public String id;

    public String loginName;

    public String name;

    public int sex;

    public String mobile;

    public String head;

    public String createDate;

    public String updateDate;

    public String delFlag;

    public String email;

    public String loginIp;

    public String loginDate;

    public String registerIp;        // 注册IP
    public String registerTime;        // 注册时间

    public int type;       //角色区分 0：amdin 1 医生 2：患者

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", head='" + head + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", email='" + email + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate='" + loginDate + '\'' +
                ", registerIp='" + registerIp + '\'' +
                ", registerTime='" + registerTime + '\'' +
//                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (token != null ? !token.equals(user.token) : user.token != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (mobile != null ? !mobile.equals(user.mobile) : user.mobile != null) return false;
        if (head != null ? !head.equals(user.head) : user.head != null) return false;
        if (createDate != null ? !createDate.equals(user.createDate) : user.createDate != null)
            return false;
        if (updateDate != null ? !updateDate.equals(user.updateDate) : user.updateDate != null)
            return false;
        if (delFlag != null ? !delFlag.equals(user.delFlag) : user.delFlag != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (loginIp != null ? !loginIp.equals(user.loginIp) : user.loginIp != null) return false;
        if (loginDate != null ? !loginDate.equals(user.loginDate) : user.loginDate != null)
            return false;
//        if (title != null ? !title.equals(user.title) : user.title != null)
//            return false;
        return false;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (head != null ? head.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        result = 31 * result + (registerIp != null ? registerIp.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
//        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.id);
        dest.writeString(this.loginName);
        dest.writeString(this.name);
        dest.writeInt(this.sex);
        dest.writeString(this.mobile);
        dest.writeString(this.head);
        dest.writeString(this.createDate);
        dest.writeString(this.updateDate);
        dest.writeString(this.delFlag);
        dest.writeString(this.email);
        dest.writeString(this.loginIp);
        dest.writeString(this.loginDate);
        dest.writeString(this.registerIp);
        dest.writeString(this.registerTime);
//        dest.writeString(this.title);
        dest.writeInt(this.type);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.token = in.readString();
        this.id = in.readString();
        this.loginName = in.readString();
        this.name = in.readString();
        this.sex = in.readInt();
        this.mobile = in.readString();
        this.head = in.readString();
        this.createDate = in.readString();
        this.updateDate = in.readString();
        this.delFlag = in.readString();
        this.email = in.readString();
        this.loginIp = in.readString();
        this.loginDate = in.readString();
        this.registerIp = in.readString();
        this.registerTime = in.readString();
        this.type = in.readInt();
//        this.title = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
