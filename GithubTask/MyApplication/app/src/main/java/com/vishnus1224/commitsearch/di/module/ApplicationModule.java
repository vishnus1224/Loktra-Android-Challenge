package com.vishnus1224.commitsearch.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vishnu on 7/16/2016.
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * Provide a single application instance wherever requested.
     * @return Single instance of the current application.
     */
    @Provides @Singleton
    Application provideApplication(){

        return application;
    }
}
