package org.d3if4070.minakitaapp.model;

import com.google.firebase.database.ServerValue;

public class PostDana {

    private String postKeyDana;
    private String titleDana;
    private String descriptionDana;
    private String danaDana;
    private String pictureDana;
    private String userIdDana;
    private String userPhotoDana;
    private Object timeStampDana ;


    public PostDana(String titleDana, String descriptionDana, String danaDana, String pictureDana, String userIdDana, String userPhotoDana) {
        this.titleDana = titleDana;
        this.descriptionDana = descriptionDana;
        this.danaDana = danaDana;
        this.pictureDana = pictureDana;
        this.userIdDana = userIdDana;
        this.userPhotoDana = userPhotoDana;
        this.timeStampDana = ServerValue.TIMESTAMP;
    }

    // make sure to have an empty constructor inside ur model class
    public PostDana() {
    }

    public String getPostKeyDana() {
        return postKeyDana;
    }

    public void setPostKeyDana(String postKeyDana) {
        this.postKeyDana = postKeyDana;
    }

    public String getTitleDana() {
        return titleDana;
    }

    public void setTitleDana(String titleDana) {
        this.titleDana = titleDana;
    }

    public String getDescriptionDana() {
        return descriptionDana;
    }

    public void setDescriptionDana(String descriptionDana) {
        this.descriptionDana = descriptionDana;
    }

    public String getDanaDana() {
        return danaDana;
    }

    public void setDanaDana(String danaDana) {
        this.danaDana = danaDana;
    }

    public String getPictureDana() {
        return pictureDana;
    }

    public void setPictureDana(String pictureDana) {
        this.pictureDana = pictureDana;
    }

    public String getUserIdDana() {
        return userIdDana;
    }

    public void setUserIdDana(String userIdDana) {
        this.userIdDana = userIdDana;
    }

    public String getUserPhotoDana() {
        return userPhotoDana;
    }

    public void setUserPhotoDana(String userPhotoDana) {
        this.userPhotoDana = userPhotoDana;
    }

    public Object getTimeStampDana() {
        return timeStampDana;
    }

    public void setTimeStampDana(Object timeStampDana) {
        this.timeStampDana = timeStampDana;
    }
}
