package com.vishnus1224.commitsearch.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishnus1224.commitsearch.R;
import com.vishnus1224.commitsearch.di.component.ActivityComponent;
import com.vishnus1224.commitsearch.di.component.DaggerActivityComponent;
import com.vishnus1224.commitsearch.di.module.ActivityModule;
import com.vishnus1224.commitsearch.model.CommitWrapper;
import com.vishnus1224.commitsearch.ui.adapter.CommitsListAdapter;
import com.vishnus1224.commitsearch.ui.view.CommitsView;

import java.util.List;

import javax.inject.Inject;

public class CommitsActivity extends BaseActivity implements CommitsView {

    private ListView commitsListView;
    private ProgressBar commitsProgressBar;

    @Inject
    CommitsListAdapter commitsListAdapter;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);

        setupViews();

        injectActivityComponent();
    }


    private void setupViews() {

        commitsListView = (ListView) findViewById(R.id.commitsListView);
        commitsProgressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    private void injectActivityComponent() {

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

        activityComponent.inject(this);

    }


    @Override
    public void showProgressBar() {

        commitsProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {

        commitsProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void showCommits(List<CommitWrapper> commitWrapperList) {

        commitsListAdapter.updateDataSet(commitWrapperList);

    }
}
