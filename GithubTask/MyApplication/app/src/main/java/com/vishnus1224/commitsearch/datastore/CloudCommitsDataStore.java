package com.vishnus1224.commitsearch.datastore;

import com.vishnus1224.commitsearch.model.CommitWrapper;
import com.vishnus1224.commitsearch.webservice.GithubWebService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Cloud data store to fetch commits from the server.
 * Created by Vishnu on 7/16/2016.
 */
public class CloudCommitsDataStore implements CommitsDataStore {

    private GithubWebService githubWebService;

    @Inject
    public CloudCommitsDataStore(GithubWebService githubWebService) {

        this.githubWebService = githubWebService;

    }

    @Override
    public Observable<List<CommitWrapper>> getCommits() {

        return githubWebService.getCommits();

    }
}
