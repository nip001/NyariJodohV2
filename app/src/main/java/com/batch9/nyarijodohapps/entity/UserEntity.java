package com.batch9.nyarijodohapps.entity;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Parcelable {


    private long id;
    private String name;
    private String password;
    private String username;
    private String jeniskelamin;
    private String nohp;
    private String umur;
    private String image;
    private Double latitude;
    private Double longitude;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeString(this.username);
        dest.writeString(this.jeniskelamin);
        dest.writeString(this.nohp);
        dest.writeString(this.umur);
        dest.writeString(this.image);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.name = source.readString();
        this.password = source.readString();
        this.username = source.readString();
        this.jeniskelamin = source.readString();
        this.nohp = source.readString();
        this.umur = source.readString();
        this.image = source.readString();
        this.latitude = (Double) source.readValue(Double.class.getClassLoader());
        this.longitude = (Double) source.readValue(Double.class.getClassLoader());
    }

    protected UserEntity(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.password = in.readString();
        this.username = in.readString();
        this.jeniskelamin = in.readString();
        this.nohp = in.readString();
        this.umur = in.readString();
        this.image = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
