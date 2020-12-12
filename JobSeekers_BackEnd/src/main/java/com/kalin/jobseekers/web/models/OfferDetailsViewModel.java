package com.kalin.jobseekers.web.models;

import java.math.BigDecimal;

public class OfferDetailsViewModel {

    private String title;
    private String description;
    private BigDecimal price;
    private String category;
    private String imageUrl;
    private UserDetailsViewModel userDetailsViewModel;

    public OfferDetailsViewModel() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserDetailsViewModel getUserDetailsViewModel() {
        return userDetailsViewModel;
    }

    public void setUserDetailsViewModel(UserDetailsViewModel userDetailsViewModel) {
        this.userDetailsViewModel = userDetailsViewModel;
    }
}
