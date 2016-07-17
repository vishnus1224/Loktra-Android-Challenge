package com.vishnus1224.commitsearch.usecase;

import com.vishnus1224.commitsearch.threads.BaseExecutor;
import com.vishnus1224.commitsearch.threads.BaseScheduler;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Base class for a use case in the app.
 * Subclasses will extend this and build the correct use case.
 * Created by Vishnu on 7/16/2016.
 */
public abstract class UseCase {

    abstract Observable buildUseCase(int page);

    private Subscription subscription;

    /**
     * Scheduler used to execute the use case.
     */
    private final BaseExecutor executionScheduler;

    /**
     * Scheduler for updating the UI i.e. the main thread scheduler.
     */
    private final BaseScheduler mainThreadScheduler;

    public UseCase(BaseExecutor executionScheduler, BaseScheduler mainThreadScheduler){

        this.executionScheduler = executionScheduler;

        this.mainThreadScheduler = mainThreadScheduler;

    }

    /**
     * Executes the use case.
     * @param page Page number of the results.
     * @param subscriber Subscriber instance.
     */
    public void execute(int page, Subscriber subscriber) {

        subscription = buildUseCase(page).subscribeOn(executionScheduler.scheduler())
                .observeOn(mainThreadScheduler.scheduler())
                .subscribe(subscriber);

    }

    /**
     * UnSubscribe the current subscription.
     */
    public void unSubscribe(){

        if(subscription != null && !subscription.isUnsubscribed()){

            subscription.unsubscribe();

        }
    }

}
