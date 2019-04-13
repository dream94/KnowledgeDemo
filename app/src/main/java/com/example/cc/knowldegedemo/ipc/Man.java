package com.example.cc.knowldegedemo.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 2019/4/13.
 */

public class Man implements Parcelable {
    private String name;
    private int age;

    public Man(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Man(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Creator<Man> CREATOR = new Creator<Man>() {
        @Override
        public Man createFromParcel(Parcel in) {
            return new Man(in);
        }

        @Override
        public Man[] newArray(int size) {
            return new Man[size];
        }
    };

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
