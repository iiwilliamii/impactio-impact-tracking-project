package com.example.impactioproject.Funding;

import com.google.firebase.database.ServerValue;

public class Fundings {

    private String postKey;
    private String title;
    private String fundingAmount;
    private String description;
    private String userId;
    private String fundSymbol;
    private Object timeStamp;

    public Fundings(String title, String fundingAmount, String description, String userId, String fundSymbol) {
        this.title = title;
        this.fundingAmount = fundingAmount;
        this.description = description;
        this.userId = userId;
        this.fundSymbol = fundSymbol;
        this.timeStamp = ServerValue.TIMESTAMP;
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

    public String getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(String fundingAmount) {
        this.fundingAmount = fundingAmount;
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

    public String getFundSymbol() {
        return fundSymbol;
    }

    public void setFundSymbol(String fundSymbol) {
        this.fundSymbol = fundSymbol;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
