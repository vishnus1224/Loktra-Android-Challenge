package com.vishnus1224.flickflipper.webservice;

import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Vishnu on 7/17/2016.
 */
public interface FlickrWebService {

    String BASE_URL = "https://api.flickr.com/";

    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    Observable<PhotoInfoWrapper> getPublicPhotoStream();
}
