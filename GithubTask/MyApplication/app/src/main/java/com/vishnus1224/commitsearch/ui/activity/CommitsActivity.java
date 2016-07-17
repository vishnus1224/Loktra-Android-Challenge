package com.vishnus1224.commitsearch.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishnus1224.commitsearch.R;
import com.vishnus1224.commitsearch.di.component.ActivityComponent;
import com.vishnus1224.commitsearch.di.component.DaggerActivityComponent;
import com.vishnus1224.commitsearch.di.module.ActivityModule;
import com.vishnus1224.commitsearch.listener.ListViewScrollToBottomListener;
import com.vishnus1224.commitsearch.model.CommitWrapper;
import com.vishnus1224.commitsearch.ui.adapter.CommitsListAdapter;
import com.vishnus1224.commitsearch.ui.presenter.CommitsPresenter;
import com.vishnus1224.commitsearch.ui.view.CommitsView;

import java.util.List;

import javax.inject.Inject;

public class CommitsActivity extends BaseActivity implements CommitsView, ListViewScrollToBottomListener.BottomHitListener {

    private ListView commitsListView;
    private ProgressBar commitsProgressBar;

    @Inject
    CommitsListAdapter commitsListAdapter;

    @Inject
    CommitsPresenter commitsPresenter;

    private ListViewScrollToBottomListener listViewScrollToBottomListener;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);

        setupViews();

        injectActivityComponent();

        setAdapter();

        initPresenter();

        setListViewScrollListener();

        fetchCommits();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        commitsPresenter.onViewDetached();

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


    private void setAdapter() {

        commitsListView.setAdapter(commitsListAdapter);

    }


    private void initPresenter() {

        commitsPresenter.onViewAttached(this);

    }


    private void setListViewScrollListener() {

        listViewScrollToBottomListener = new ListViewScrollToBottomListener(this);

        commitsListView.setOnScrollListener(listViewScrollToBottomListener);

    }


    private void fetchCommits() {

        commitsPresenter.fetchCommits();

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

    @Override
    public void showError(String message) {

        Snackbar.make(commitsListView, message, Snackbar.LENGTH_INDEFINITE).
                setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        commitsPresenter.retryLoadingCommits();

                    }
                }).show();

    }

    @Override
    public void onBottomHit() {

        commitsPresenter.loadCommitsOnNextPage();

    }
}
