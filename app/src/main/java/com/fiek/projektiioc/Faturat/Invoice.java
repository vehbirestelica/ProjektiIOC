package com.fiek.projektiioc.Faturat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
@IgnoreExtraProperties
public class Invoice implements Parcelable {

        private String invTitle;
        private String invType;
        private @ServerTimestamp Date timestamp;
        private String invSum;
        private String invComment;
        private String invPhotoUri;
        private String invId;
        private String userId;
        private String usersName;



    public Invoice(String invTitle,String invType, Date timestamp,String invSum,String invComment,String invPhotoUri, String invId,String userId, String usersName)
    {
        this.invTitle = invTitle;
        this.invType = invType;
        this.timestamp = timestamp;
        this.invSum = invSum;
        this.invComment = invComment;
        this.invPhotoUri = invPhotoUri;
        this.invId = invId;
        this.userId = userId;
        this.usersName = usersName;
    }
    public Invoice(){

    }

    protected Invoice(Parcel in) {
        invTitle = in.readString();
        invType = in.readString();
        invSum = in.readString();
        invComment = in.readString();
        invPhotoUri = in.readString();
        invId = in.readString();
        userId = in.readString();
        usersName = in.readString();
    }

    public static final Creator<Invoice> CREATOR = new Creator<Invoice>() {
        @Override
        public Invoice createFromParcel(Parcel in) {
            return new Invoice(in);
        }

        @Override
        public Invoice[] newArray(int size) {
            return new Invoice[size];
        }
    };

    public String getInvTitle() {
        return invTitle;
    }

    public void setInvTitle(String invTitle) {
        this.invTitle = invTitle;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getInvSum() {
        return invSum;
    }

    public void setInvSum(String invSum) {
        this.invSum = invSum;
    }

    public String getInvComment() {
        return invComment;
    }

    public void setInvComment(String invComment) {
        this.invComment = invComment;
    }

    public String getInvPhotoUri() {
        return invPhotoUri;
    }

    public void setInvPhotoUri(String invPhotoUri) {
        this.invPhotoUri = invPhotoUri;
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(invTitle);
        dest.writeString(invType);
        dest.writeString(invSum);
        dest.writeString(invComment);
        dest.writeString(invPhotoUri);
        dest.writeString(invId);
        dest.writeString(userId);
        dest.writeString(usersName);
    }
}

