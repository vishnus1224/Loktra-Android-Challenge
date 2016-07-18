package com.vishnus1224.flickflipper.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.vishnus1224.flickflipper.R;
import com.vishnus1224.flickflipper.di.component.ActivityComponent;
import com.vishnus1224.flickflipper.di.component.DaggerActivityComponent;
import com.vishnus1224.flickflipper.di.module.ActivityModule;
import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;
import com.vishnus1224.flickflipper.ui.presenter.PhotoStreamPresenter;
import com.vishnus1224.flickflipper.ui.view.PhotoStreamView;

import javax.inject.Inject;


public class PhotoStreamActivity extends BaseActivity implements PhotoStreamView{

    private GridView photoStreamGridView;
    private ProgressBar progressBar;

    @Inject
    PhotoStreamPresenter photoStreamPresenter;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_stream);

        setupViews();

        injectDependencies();

        setupPresenter();

        fetchPublicPhotoStream();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        photoStreamPresenter.onViewDetached();
    }

    private void setupViews() {

        photoStreamGridView = (GridView) findViewById(R.id.photoStreamGridView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    private void injectDependencies() {

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

        activityComponent.inject(this);

    }


    private void setupPresenter() {

        photoStreamPresenter.onViewAttached(this);

    }


    private void fetchPublicPhotoStream() {

        photoStreamPresenter.getPublicPhotoStream();

    }



    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showPhotos(PhotoInfoWrapper photoInfoWrapper) {

    }

    @Override
    public void showError(String message) {

        Snackbar.make(photoStreamGridView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        photoStreamPresenter.retryLoadingPhotoStream();

                    }
                })
                .show();

    }
}
