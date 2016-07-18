package com.vishnus1224.flickflipper.model;

import java.util.List;

/**
 * Created by Vishnu on 7/17/2016.
 */
public class PhotoInfoWrapper {

    private List<PhotoInfo> items;

    public List<PhotoInfo> getItems() {
        return items;
    }

    public PhotoInfoWrapper(List<PhotoInfo> items) {
        this.items = items;
    }
}
