package com.vishnus1224.commitsearch.repository;

import com.vishnus1224.commitsearch.model.CommitWrapper;

import java.util.List;

import rx.Observable;

/**
 * Repository for interfacing with the data store to perform operations on commits.
 * Created by Vishnu on 7/16/2016.
 */
public interface CommitsRepository {

    /**
     * Get a list of commits from the repository.
     * @param page Page number of the results.
     * @return Observable emitting a list of commits.
     */
    Observable<List<CommitWrapper>> getCommits(int page);
}
