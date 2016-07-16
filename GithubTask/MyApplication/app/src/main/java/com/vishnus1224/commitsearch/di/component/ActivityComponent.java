package com.vishnus1224.commitsearch.di.component;

import com.vishnus1224.commitsearch.di.module.ActivityModule;
import com.vishnus1224.commitsearch.di.scope.PerActivity;
import com.vishnus1224.commitsearch.ui.activity.CommitsActivity;

import dagger.Component;

/**
 * Created by Vishnu on 7/16/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CommitsActivity commitsActivity);
}
