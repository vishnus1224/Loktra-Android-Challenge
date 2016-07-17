package com.vishnus1224.flickflipper.ui.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.vishnus1224.flickflipper.R;
import com.vishnus1224.flickflipper.di.component.ActivityComponent;
import com.vishnus1224.flickflipper.di.component.DaggerActivityComponent;
import com.vishnus1224.flickflipper.di.module.ActivityModule;


public class PhotoStreamActivity extends BaseActivity {

    private GridView photoStreamGridView;
    private ProgressBar progressBar;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_stream);

        setupViews();

        injectDependencies();
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
}
