package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Encounter implements Parcelable, Entity{

    public String id;
    public User patient;           //患者
    public String registerCode;    // 挂号编号
    public String description;     // 病例描述
    public int status;             // 状态 0：待处理 1 正在处理 2：处理完成
    public String createDate;
    public String updateDate;
    public String createBy;
    public String updateBy;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
//        dest.write(this.patient);
        dest.writeString(this.registerCode);
        dest.writeInt(this.status);
        dest.writeString(this.description);
        dest.writeString(this.createDate);
        dest.writeString(this.createBy);
        dest.writeString(this.updateBy);
        dest.writeString(this.updateDate);
    }

    public Encounter() {
    }

    protected Encounter(Parcel in) {
        this.id = in.readString();
//        this.patient = in.readValue();
        this.registerCode = in.readString();
        this.description = in.readString();
        this.status = in.readInt();
        this.createDate = in.readString();
        this.updateDate = in.readString();
        this.createBy = in.readString();
        this.updateBy = in.readString();
    }

    public static final Creator<Encounter> CREATOR = new Creator<Encounter>() {
        @Override
        public Encounter createFromParcel(Parcel source) {
            return new Encounter(source);
        }

        @Override
        public Encounter[] newArray(int size) {
            return new Encounter[size];
        }
    };
}
