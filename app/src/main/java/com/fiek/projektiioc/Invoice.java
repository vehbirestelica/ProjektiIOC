package com.fiek.projektiioc;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
@IgnoreExtraProperties
public class Invoice {

        private String invTitle;
        private String invType;
        private @ServerTimestamp Date timestamp;
        private String invSum;
        private String invComment;
        private String invPhotoUri;
        private String invId;
        private String userId;
    public Invoice(String invTitle,String invType, Date timestamp,String invSum,String invComment,String invPhotoUri, String invId,String userId)
    {
        this.invTitle = invTitle;
        this.invType = invType;
        this.timestamp = timestamp;
        this.invSum = invSum;
        this.invComment = invComment;
        this.invPhotoUri = invPhotoUri;
        this.invId = invId;
        this.userId = userId;
    }
    public Invoice(){

    }

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
}

