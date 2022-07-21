package com.example.impactioproject.PostModels;

import com.google.firebase.database.ServerValue;

public class Post {

    private String postKey;
    private String title;
    private String description;
    private String userId;
    private Object timeStamp;

    public Post(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public Post() {

    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
