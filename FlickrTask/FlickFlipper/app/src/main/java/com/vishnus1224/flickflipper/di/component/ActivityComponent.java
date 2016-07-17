package com.vishnus1224.flickflipper.di.component;

import com.vishnus1224.flickflipper.di.module.ActivityModule;
import com.vishnus1224.flickflipper.di.scope.PerActivity;
import com.vishnus1224.flickflipper.ui.activity.PhotoStreamActivity;

import dagger.Component;

/**
 * Created by Vishnu on 7/17/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(PhotoStreamActivity photoStreamActivity);

}
