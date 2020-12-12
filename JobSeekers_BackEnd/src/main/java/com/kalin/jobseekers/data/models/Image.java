package com.kalin.jobseekers.data.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Image extends BaseEntity{

    @Column
    private String idInCloud;

    @Column
    private String url;

    public Image() {
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
