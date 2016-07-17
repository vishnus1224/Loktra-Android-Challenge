package com.vishnus1224.flickflipper.model;

/**
 * Models the info from an object in the flickr api.
 * Created by Vishnu on 7/17/2016.
 */
public class PhotoInfo {

    private String title;
    private String link;
    private PhotoInfoMedia media;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public PhotoInfoMedia getMedia() {
        return media;
    }
}
