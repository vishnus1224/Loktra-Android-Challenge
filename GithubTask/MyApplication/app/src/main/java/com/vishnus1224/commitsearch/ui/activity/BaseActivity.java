package com.vishnus1224.commitsearch.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.vishnus1224.commitsearch.GithubCommits;
import com.vishnus1224.commitsearch.di.component.ApplicationComponent;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent getApplicationComponent(){

        return getGithubCommits().getApplicationComponent();

    }

    private GithubCommits getGithubCommits(){

        return (GithubCommits) getApplication();

    }
}
