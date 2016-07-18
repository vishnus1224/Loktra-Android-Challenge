package com.vishnus1224.flickflipper.ui.view;

import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;

/**
 * Created by Vishnu on 7/18/2016.
 */
public interface PhotoStreamView {

    void showProgressBar();

    void hideProgressBar();

    void showPhotos(PhotoInfoWrapper photoInfoWrapper);

    void showError(String message);
}
