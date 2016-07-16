package com.vishnus1224.commitsearch.di.component;

import com.vishnus1224.commitsearch.GithubCommits;
import com.vishnus1224.commitsearch.di.module.ApplicationModule;
import com.vishnus1224.commitsearch.webservice.GithubWebService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Injects app level dependencies specified in the application module.
 * Created by Vishnu on 7/16/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //expose to dependent components.
    GithubWebService githubWebService();

    void inject(GithubCommits githubCommits);
}
