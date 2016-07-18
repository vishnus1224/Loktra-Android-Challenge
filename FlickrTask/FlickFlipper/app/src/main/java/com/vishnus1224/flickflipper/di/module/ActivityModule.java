package com.vishnus1224.flickflipper.di.module;

import android.app.Activity;
import android.view.LayoutInflater;

import com.vishnus1224.flickflipper.datasource.PhotoStreamCloudDataSource;
import com.vishnus1224.flickflipper.datasource.PhotoStreamDataSource;
import com.vishnus1224.flickflipper.di.scope.PerActivity;
import com.vishnus1224.flickflipper.repository.PhotoStreamRepository;
import com.vishnus1224.flickflipper.repository.PhotoStreamRepositoryImpl;
import com.vishnus1224.flickflipper.usecase.GetPublicPhotoStreamUseCase;
import com.vishnus1224.flickflipper.usecase.UseCase;

import javax.inject.Named;

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

    /**
     * Provides the repository implementation for accessing the public photo stream.
     * @param photoStreamRepositoryImpl PhotoStreamRepositoryImpl instance.
     * @return PhotoStreamRepositoryImpl instance.
     */
    @Provides @PerActivity
    PhotoStreamRepository providePhotoStreamRepository(PhotoStreamRepositoryImpl photoStreamRepositoryImpl){

        return photoStreamRepositoryImpl;

    }

    /**
     * Provides use case for fetching public photo stream.
     * @param getPublicPhotoStreamUseCase GetPublicPhotoStreamUseCase instance.
     * @return GetPublicPhotoStreamUseCase instance.
     */
    @Provides @PerActivity @Named("publicPhotoStream")
    UseCase providePublicPhotoStreamUseCase(GetPublicPhotoStreamUseCase getPublicPhotoStreamUseCase){

        return getPublicPhotoStreamUseCase;

    }

    @Provides @PerActivity
    LayoutInflater provideLayoutInflater(){

        return activity.getLayoutInflater();

    }
}
