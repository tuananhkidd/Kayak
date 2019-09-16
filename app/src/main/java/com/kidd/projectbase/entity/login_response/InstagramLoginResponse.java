package com.kidd.projectbase.entity.login_response;

import com.google.gson.annotations.SerializedName;

public class InstagramLoginResponse {
    private String id;
    @SerializedName("username")
    private String userName;
    @SerializedName("profile_picture")
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
