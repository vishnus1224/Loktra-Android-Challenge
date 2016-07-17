package com.vishnus1224.flickflipper;

import android.app.Application;

import com.vishnus1224.flickflipper.di.component.ApplicationComponent;
import com.vishnus1224.flickflipper.di.component.DaggerApplicationComponent;
import com.vishnus1224.flickflipper.di.module.ApplicationModule;

/**
 * Created by Vishnu on 7/17/2016.
 */
public class FlickrFlipper extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        injectApplicationComponent();
    }

    private void injectApplicationComponent() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){

        return applicationComponent;

    }
}
