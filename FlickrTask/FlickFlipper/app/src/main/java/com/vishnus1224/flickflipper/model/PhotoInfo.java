package com.vishnus1224.flickflipper.model;

/**
 * Models the info from an object in the flickr api.
 * Created by Vishnu on 7/17/2016.
 */
public class PhotoInfo {

    private String title;
    private String link;
    private PhotoInfoMedia media;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public PhotoInfoMedia getMedia() {
        return media;
    }

    public String getDescription() {
        return description;
    }

    public PhotoInfo(String title, String link, PhotoInfoMedia media, String description) {
        this.title = title;
        this.link = link;
        this.media = media;
        this.description = description;
    }
}
