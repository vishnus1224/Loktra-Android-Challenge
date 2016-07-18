package com.vishnus1224.flickflipper;

import com.vishnus1224.flickflipper.model.PhotoInfo;
import com.vishnus1224.flickflipper.model.PhotoInfoMedia;
import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class MockDataFactory {

    public static final PhotoInfoWrapper mockPhotoInfoWrapper(){

        List<PhotoInfo> photoInfoList = new ArrayList<>();

        photoInfoList.add(mockPhotoInfo());

        PhotoInfoWrapper photoInfoWrapper = new PhotoInfoWrapper(photoInfoList);


        return photoInfoWrapper;

    }

    public static final PhotoInfo mockPhotoInfo(){

        PhotoInfoMedia photoInfoMedia = mockPhotoInfoMedia();

        PhotoInfo photoInfo = new PhotoInfo("Amazed", "bin/media/image", photoInfoMedia, "test description");

        return photoInfo;

    }

    public static final PhotoInfoMedia mockPhotoInfoMedia(){

        PhotoInfoMedia photoInfoMedia = new PhotoInfoMedia("volumes/data/src");

        return photoInfoMedia;

    }
}
