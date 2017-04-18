package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by whatisjava on 15/11/11.
 */
public class QiNiuToken implements Parcelable, Entity {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QiNiuToken that = (QiNiuToken) o;

        return token != null ? token.equals(that.token) : that.token == null;

    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "QiNiuToken{" +
                "token='" + token + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
    }

    public QiNiuToken() {
    }

    protected QiNiuToken(Parcel in) {
        this.token = in.readString();
    }

    public static final Creator<QiNiuToken> CREATOR = new Creator<QiNiuToken>() {
        @Override
        public QiNiuToken createFromParcel(Parcel source) {
            return new QiNiuToken(source);
        }

        @Override
        public QiNiuToken[] newArray(int size) {
            return new QiNiuToken[size];
        }
    };
}
