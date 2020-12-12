package com.kalin.jobseekers.service.models;

public class ImageServiceModel extends BaseServiceModel {

    private String idInCloud;

    private String url;

    public ImageServiceModel() {
    }

    public String getIdInCloud() {
        return idInCloud;
    }

    public void setIdInCloud(String idInCloud) {
        this.idInCloud = idInCloud;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
