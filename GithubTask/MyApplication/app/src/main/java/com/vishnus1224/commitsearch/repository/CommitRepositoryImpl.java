package com.vishnus1224.commitsearch.repository;

import com.vishnus1224.commitsearch.datastore.CloudCommitsDataStore;
import com.vishnus1224.commitsearch.model.CommitWrapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class CommitRepositoryImpl implements CommitsRepository {
    private CloudCommitsDataStore cloudCommitsDataStore;

    @Inject
    public CommitRepositoryImpl(CloudCommitsDataStore cloudCommitsDataStore) {
        this.cloudCommitsDataStore = cloudCommitsDataStore;
    }

    @Override
    public Observable<List<CommitWrapper>> getCommits() {
        return cloudCommitsDataStore.getCommits();
    }
}
