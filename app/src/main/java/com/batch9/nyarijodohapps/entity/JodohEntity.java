package com.batch9.nyarijodohapps.entity;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JodohEntity implements Parcelable {

    private long id;
    private UserEntity iduser;
    private UserEntity idjodoh;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeParcelable(this.iduser, flags);
        dest.writeParcelable(this.idjodoh, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.iduser = source.readParcelable(UserEntity.class.getClassLoader());
        this.idjodoh = source.readParcelable(UserEntity.class.getClassLoader());
    }

    protected JodohEntity(Parcel in) {
        this.id = in.readLong();
        this.iduser = in.readParcelable(UserEntity.class.getClassLoader());
        this.idjodoh = in.readParcelable(UserEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<JodohEntity> CREATOR = new Parcelable.Creator<JodohEntity>() {
        @Override
        public JodohEntity createFromParcel(Parcel source) {
            return new JodohEntity(source);
        }

        @Override
        public JodohEntity[] newArray(int size) {
            return new JodohEntity[size];
        }
    };
}
