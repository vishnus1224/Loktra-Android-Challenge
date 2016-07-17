package com.vishnus1224.flickflipper.di.component;

import com.vishnus1224.flickflipper.FlickrFlipper;
import com.vishnus1224.flickflipper.di.module.ApplicationModule;
import com.vishnus1224.flickflipper.di.scope.PerApplication;
import com.vishnus1224.flickflipper.threads.BaseExecutor;
import com.vishnus1224.flickflipper.threads.BaseScheduler;
import com.vishnus1224.flickflipper.webservice.FlickrWebService;

import dagger.Component;

/**
 * Created by Vishnu on 7/17/2016.
 */
@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //exposed to sub graphs.
    FlickrWebService flickerWebService();
    BaseScheduler baseScheduler();
    BaseExecutor baseExecutor();

    void inject(FlickrFlipper flickrFlipper);

}
