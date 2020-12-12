package com.kalin.jobseekers.web.models;

public class UserDetailsViewModel {

    private String username;
    private String email;
    private String phoneNumber;
    private Long numberOfOffers;

    public UserDetailsViewModel() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNumberOfOffers() {
        return numberOfOffers;
    }

    public void setNumberOfOffers(Long numberOfOffers) {
        this.numberOfOffers = numberOfOffers;
    }
}
