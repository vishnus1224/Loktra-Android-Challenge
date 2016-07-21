package com.vishnus1224.flickflipper.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.vishnus1224.flickflipper.R;
import com.vishnus1224.flickflipper.di.component.ActivityComponent;
import com.vishnus1224.flickflipper.di.component.DaggerActivityComponent;
import com.vishnus1224.flickflipper.di.module.ActivityModule;
import com.vishnus1224.flickflipper.manager.AnimationManager;
import com.vishnus1224.flickflipper.model.PhotoInfo;
import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;
import com.vishnus1224.flickflipper.ui.adapter.PhotoStreamGridAdapter;
import com.vishnus1224.flickflipper.ui.presenter.PhotoStreamPresenter;
import com.vishnus1224.flickflipper.ui.view.PhotoStreamView;

import javax.inject.Inject;


public class PhotoStreamActivity extends BaseActivity implements PhotoStreamView, AdapterView.OnItemClickListener {

    private GridView photoStreamGridView;
    private ProgressBar progressBar;

    @Inject
    PhotoStreamPresenter photoStreamPresenter;

    @Inject
    PhotoStreamGridAdapter photoStreamGridAdapter;

    @Inject
    public AnimationManager animationManager;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_stream);

        setupViews();

        injectDependencies();

        setupPresenter();

        setupAdapter();

        initAnimationManager();

        fetchPublicPhotoStream();

        setGridViewClickListener();

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

    private void setupAdapter() {

        photoStreamGridView.setAdapter(photoStreamGridAdapter);

    }


    private void initAnimationManager() {

        animationManager.init();

    }


    private void fetchPublicPhotoStream() {

        photoStreamPresenter.getPublicPhotoStream();

    }


    private void setGridViewClickListener() {

        photoStreamGridView.setOnItemClickListener(this);

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

        photoStreamGridAdapter.updateDataSet(photoInfoWrapper.getItems());

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        PhotoInfo photoInfo = (PhotoInfo) photoStreamGridAdapter.getItem(i);

        PhotoStreamGridAdapter.PhotoStreamViewHolder holder = (PhotoStreamGridAdapter.PhotoStreamViewHolder) view.getTag();

        animateViews(photoInfo, holder.frontView, holder.backView);
    }


    private void animateViews(PhotoInfo photoInfo, ImageView frontView, View backView) {

        if(photoInfo.getVisibleSide() == PhotoInfo.Side.FRONT || photoInfo.getVisibleSide() == null){

            animationManager.setFlipOutAnimationTarget(frontView);
            animationManager.setFlipInAnimationTarget(backView);

            photoInfo.setVisibleSide(PhotoInfo.Side.BACK);

        }else{

            animationManager.setFlipOutAnimationTarget(backView);
            animationManager.setFlipInAnimationTarget(frontView);

            photoInfo.setVisibleSide(PhotoInfo.Side.FRONT);

        }

        animationManager.startAnimations();
    }
}
