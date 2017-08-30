package com.example.rathods.schoolapp_v4.User.Models;

/**
 * Created by RATHOD'S on 6/10/2017.
 */

public class ModelGallery {
    private String url, title;
    private String gallery_id;
    private String image_count;

    public String getImage_count() {
        return image_count;
    }

    public void setImage_count(String image_count) {
        this.image_count = image_count;
    }

    public String getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(String gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
