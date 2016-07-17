package com.vishnus1224.commitsearch.datastore;

import com.vishnus1224.commitsearch.model.CommitWrapper;

import java.util.List;

import rx.Observable;

/**
 * Data store holding commits.
 * Created by Vishnu on 7/16/2016.
 */
public interface CommitsDataStore {

    /**
     * Get a list of commits from the underlying data store.
     * @param page Page number of the results
     * @return A list of commits;
     */
    Observable<List<CommitWrapper>> getCommits(int page);
}
