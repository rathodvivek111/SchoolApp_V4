package com.example.rathods.schoolapp_v4.User.Models;

import java.io.Serializable;

/**
 * Created by RATHOD'S on 6/10/2017.
 */

public class ModelPictures implements Serializable {
    private String url, title;

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
