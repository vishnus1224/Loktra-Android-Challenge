package com.vishnus1224.commitsearch;

import android.app.Application;

import com.vishnus1224.commitsearch.di.component.ApplicationComponent;
import com.vishnus1224.commitsearch.di.component.DaggerApplicationComponent;
import com.vishnus1224.commitsearch.di.module.ApplicationModule;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class GithubCommits extends Application {

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
