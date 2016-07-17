package com.vishnus1224.commitsearch.usecase;

import com.vishnus1224.commitsearch.repository.CommitsRepository;
import com.vishnus1224.commitsearch.threads.BaseExecutor;
import com.vishnus1224.commitsearch.threads.BaseScheduler;

import javax.inject.Inject;

import rx.Observable;

/**
 * Use case for getting commits from the data store.
 * Created by Vishnu on 7/16/2016.
 */
public class GetCommitsUseCase extends UseCase {

    private CommitsRepository commitsRepository;

    @Inject
    public GetCommitsUseCase(CommitsRepository commitsRepository, BaseExecutor executionScheduler
            , BaseScheduler mainThreadScheduler) {
        super(executionScheduler, mainThreadScheduler);

        this.commitsRepository = commitsRepository;
    }

    @Override
    Observable buildUseCase() {

        return commitsRepository.getCommits();

    }
}
