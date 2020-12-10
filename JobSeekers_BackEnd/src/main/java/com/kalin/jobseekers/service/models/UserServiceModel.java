package com.kalin.jobseekers.service.models;

import java.util.List;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    private List<OfferServiceModel> offers;

    private List<OfferServiceModel> favouriteOffers;

    private Set<RoleServiceModel> authorities;

    public UserServiceModel() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<OfferServiceModel> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferServiceModel> offers) {
        this.offers = offers;
    }

    public List<OfferServiceModel> getFavouriteOffers() {
        return favouriteOffers;
    }

    public void setFavouriteOffers(List<OfferServiceModel> favouriteOffers) {
        this.favouriteOffers = favouriteOffers;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }
}
