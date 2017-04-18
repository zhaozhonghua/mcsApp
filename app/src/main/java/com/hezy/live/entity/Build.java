package com.hezy.live.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Build implements Parcelable, Entity {

	public int version_code;

	public int major;

	public int minor;

	public int patch;

	public String changelog;

	public String url;

	public String created_at;

	@Override
	public String toString() {
		return "Build{" +
				"version_code=" + version_code +
				", major=" + major +
				", minor=" + minor +
				", patch=" + patch +
				", changelog='" + changelog + '\'' +
				", url='" + url + '\'' +
				", created_at='" + created_at + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Build build = (Build) o;

		if (version_code != build.version_code) return false;
		if (major != build.major) return false;
		if (minor != build.minor) return false;
		if (patch != build.patch) return false;
		if (changelog != null ? !changelog.equals(build.changelog) : build.changelog != null)
			return false;
		if (url != null ? !url.equals(build.url) : build.url != null) return false;
		return created_at != null ? created_at.equals(build.created_at) : build.created_at == null;

	}

	@Override
	public int hashCode() {
		int result = version_code;
		result = 31 * result + major;
		result = 31 * result + minor;
		result = 31 * result + patch;
		result = 31 * result + (changelog != null ? changelog.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
		return result;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.version_code);
		dest.writeInt(this.major);
		dest.writeInt(this.minor);
		dest.writeInt(this.patch);
		dest.writeString(this.changelog);
		dest.writeString(this.url);
		dest.writeString(this.created_at);
	}

	public Build() {
	}

	protected Build(Parcel in) {
		this.version_code = in.readInt();
		this.major = in.readInt();
		this.minor = in.readInt();
		this.patch = in.readInt();
		this.changelog = in.readString();
		this.url = in.readString();
		this.created_at = in.readString();
	}

	public static final Creator<Build> CREATOR = new Creator<Build>() {
		@Override
		public Build createFromParcel(Parcel source) {
			return new Build(source);
		}

		@Override
		public Build[] newArray(int size) {
			return new Build[size];
		}
	};
}
