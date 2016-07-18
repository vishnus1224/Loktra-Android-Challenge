package com.vishnus1224.flickflipper.ui.presenter;

import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;
import com.vishnus1224.flickflipper.ui.view.PhotoStreamView;
import com.vishnus1224.flickflipper.usecase.UseCase;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamPresenter {

    private PhotoStreamView photoStreamView;

    private UseCase useCase;

    @Inject
    public PhotoStreamPresenter(@Named("publicPhotoStream")UseCase useCase) {

        this.useCase = useCase;

    }

    public void onViewAttached(PhotoStreamView photoStreamView){

        this.photoStreamView = photoStreamView;

    }

    public void onViewDetached(){

        useCase.unSubscribe();

    }

    public void getPublicPhotoStream(){

        photoStreamView.showProgressBar();

        useCase.execute(new PhotoStreamSubscriber());

    }

    public void retryLoadingPhotoStream(){

        photoStreamView.showProgressBar();

        useCase.execute(new PhotoStreamSubscriber());

    }

    private final class PhotoStreamSubscriber extends Subscriber<PhotoInfoWrapper> {

        @Override
        public void onCompleted() {

            photoStreamView.hideProgressBar();

        }

        @Override
        public void onError(Throwable e) {

            photoStreamView.hideProgressBar();

            photoStreamView.showError("An error occurred");
        }

        @Override
        public void onNext(PhotoInfoWrapper photoInfoWrapper) {

            photoStreamView.showPhotos(photoInfoWrapper);

        }
    }
}
