package com.vishnus1224.flickflipper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu on 7/17/2016.
 */
public class PhotoInfoMedia {

    @SerializedName("m")
    private String mediaUrl;

    public PhotoInfoMedia(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
}
