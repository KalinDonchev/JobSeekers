package com.kalin.jobseekers.web.models;

public class DeleteOfferRequest {

    private String offerId;

    private String username;


    public DeleteOfferRequest() {
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
