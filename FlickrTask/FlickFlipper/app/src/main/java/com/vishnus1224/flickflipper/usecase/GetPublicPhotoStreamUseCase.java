package com.vishnus1224.flickflipper.usecase;

import com.vishnus1224.flickflipper.repository.PhotoStreamRepository;
import com.vishnus1224.flickflipper.threads.BaseExecutor;
import com.vishnus1224.flickflipper.threads.BaseScheduler;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class GetPublicPhotoStreamUseCase extends UseCase {

    private PhotoStreamRepository photoStreamRepository;

    @Inject
    public GetPublicPhotoStreamUseCase(PhotoStreamRepository photoStreamRepository,
                                       BaseExecutor baseExecutor, BaseScheduler baseScheduler) {
        super(baseExecutor, baseScheduler);

        this.photoStreamRepository = photoStreamRepository;
    }

    @Override
    Observable buildUseCase() {

        return photoStreamRepository.getPublicPhotoStream();

    }
}
