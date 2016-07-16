package com.vishnus1224.commitsearch.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.vishnus1224.commitsearch.R;
import com.vishnus1224.commitsearch.model.CommitWrapper;
import com.vishnus1224.commitsearch.ui.view.CommitsView;

import java.util.List;

public class CommitsActivity extends AppCompatActivity implements CommitsView {

    private ListView commitsListView;
    private ProgressBar commitsProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);

        setupViews();
    }

    private void setupViews() {

        commitsListView = (ListView) findViewById(R.id.commitsListView);
        commitsProgressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void showProgressBar() {


    }

    @Override
    public void hideProgressBar() {


    }

    @Override
    public void showCommits(List<CommitWrapper> commitWrapperList) {

        
    }
}
