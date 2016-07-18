package com.vishnus1224.flickflipper.datasource;

import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;
import com.vishnus1224.flickflipper.webservice.FlickrWebService;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamCloudDataSource implements PhotoStreamDataSource {

    private FlickrWebService flickrWebService;

    @Inject
    public PhotoStreamCloudDataSource(FlickrWebService flickrWebService) {
        this.flickrWebService = flickrWebService;
    }

    @Override
    public Observable<PhotoInfoWrapper> getPublicPhotoStream() {

        return flickrWebService.getPublicPhotoStream();

    }
}
