package com.vishnus1224.commitsearch.di.component;

import com.vishnus1224.commitsearch.GithubCommits;
import com.vishnus1224.commitsearch.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Injects app level dependencies specified in the application module.
 * Created by Vishnu on 7/16/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(GithubCommits githubCommits);
}
