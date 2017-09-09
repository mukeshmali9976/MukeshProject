package com.mukeshproject.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo pc on 03/09/2017.
 */

public class BaseModel implements Parcelable {

    public String message;
    public String token;
    public String status;
    public String header;

    public BaseModel() {
    }

    protected BaseModel(Parcel in) {
        message = in.readString();
        token = in.readString();
        status = in.readString();
        header = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(token);
        dest.writeString(status);
        dest.writeString(header);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel in) {
            return new BaseModel(in);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
