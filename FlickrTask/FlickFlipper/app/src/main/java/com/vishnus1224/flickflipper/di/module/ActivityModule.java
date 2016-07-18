package com.vishnus1224.flickflipper.di.module;

import android.app.Activity;

import com.vishnus1224.flickflipper.datasource.PhotoStreamCloudDataSource;
import com.vishnus1224.flickflipper.datasource.PhotoStreamDataSource;
import com.vishnus1224.flickflipper.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vishnu on 7/17/2016.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Provide the current activity.
     * @return current activity.
     */
    @Provides @PerActivity
    Activity provideActivity(){

        return activity;

    }

    /**
     * Provides the cloud data source for fetching the public photo stream.
     * @param photoStreamCloudDataSource PhotoStreamCloudDataSource instance.
     * @return PhotoStreamCloudDataSource instance.
     */
    @Provides @PerActivity
    PhotoStreamDataSource provideCloudDataSource(PhotoStreamCloudDataSource photoStreamCloudDataSource){

        return photoStreamCloudDataSource;

    }
}
