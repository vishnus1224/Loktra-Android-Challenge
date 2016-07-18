package com.vishnus1224.flickflipper.datasource;

import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;

import rx.Observable;

/**
 * Created by Vishnu on 7/18/2016.
 */
public interface PhotoStreamDataSource {

    /**
     * Get observable emitting items in the public photo stream.
     * @return Items from the public photo stream.
     */
    Observable<PhotoInfoWrapper> getPublicPhotoStream();
}
