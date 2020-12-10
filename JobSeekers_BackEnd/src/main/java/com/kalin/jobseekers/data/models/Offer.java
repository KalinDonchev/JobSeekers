package com.kalin.jobseekers.data.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Offer extends BaseEntity {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    // TRY TO UPLOAD MULTIPLE PHOTOS
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "cateogry_id")
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "favouriteOffers")
    private List<User> usersFav;


    public Offer() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsersFav() {
        return usersFav;
    }

    public void setUsersFav(List<User> usersFav) {
        this.usersFav = usersFav;
    }
}
