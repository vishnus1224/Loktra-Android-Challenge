package com.vishnus1224.flickflipper.repository;

import com.vishnus1224.flickflipper.datasource.PhotoStreamDataSource;
import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamRepositoryImpl implements PhotoStreamRepository {

    private PhotoStreamDataSource photoStreamDataSource;

    @Inject
    public PhotoStreamRepositoryImpl(PhotoStreamDataSource photoStreamDataSource) {
        this.photoStreamDataSource = photoStreamDataSource;
    }

    @Override
    public Observable<PhotoInfoWrapper> getPublicPhotoStream() {

        return photoStreamDataSource.getPublicPhotoStream();

    }
}
