package com.vishnus1224.flickflipper.di.component;

import com.vishnus1224.flickflipper.FlickrFlipper;
import com.vishnus1224.flickflipper.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vishnu on 7/17/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(FlickrFlipper flickrFlipper);

}
