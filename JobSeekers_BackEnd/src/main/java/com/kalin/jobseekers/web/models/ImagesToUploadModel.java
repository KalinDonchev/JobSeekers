package com.kalin.jobseekers.web.models;

public class ImagesToUploadModel extends DeleteOfferRequest{

    private String[] images;

    public ImagesToUploadModel() {
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
