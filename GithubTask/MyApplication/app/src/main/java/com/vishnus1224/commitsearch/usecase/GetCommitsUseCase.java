package com.vishnus1224.commitsearch.usecase;

import com.vishnus1224.commitsearch.repository.CommitsRepository;
import com.vishnus1224.commitsearch.threads.BaseScheduler;

import javax.inject.Named;

import rx.Observable;

/**
 * Use case for getting commits from the data store.
 * Created by Vishnu on 7/16/2016.
 */
public class GetCommitsUseCase extends UseCase {

    private CommitsRepository commitsRepository;

    public GetCommitsUseCase(CommitsRepository commitsRepository, @Named("io") BaseScheduler executionScheduler
            , @Named("main") BaseScheduler mainThreadScheduler) {
        super(executionScheduler, mainThreadScheduler);

        this.commitsRepository = commitsRepository;
    }

    @Override
    Observable buildUseCase() {

        return commitsRepository.getCommits();

    }
}
