package com.vishnus1224.flickflipper.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.vishnus1224.flickflipper.FlickrFlipper;
import com.vishnus1224.flickflipper.di.component.ApplicationComponent;

/**
 * Created by Vishnu on 7/17/2016.
 */
public class BaseActivity extends AppCompatActivity {

    public ApplicationComponent getApplicationComponent(){

        return getFlickrFlipper().getApplicationComponent();

    }

    private FlickrFlipper getFlickrFlipper(){

        return (FlickrFlipper) getApplication();

    }
}
