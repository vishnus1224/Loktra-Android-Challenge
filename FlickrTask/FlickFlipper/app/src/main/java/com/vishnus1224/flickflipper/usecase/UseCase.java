package com.vishnus1224.flickflipper.usecase;

import com.vishnus1224.flickflipper.threads.BaseExecutor;
import com.vishnus1224.flickflipper.threads.BaseScheduler;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Vishnu on 7/18/2016.
 */
public abstract class UseCase {

    abstract Observable buildUseCase();

    private final BaseExecutor baseExecutor;

    private final BaseScheduler baseScheduler;

    private Subscription subscription;

    public UseCase(BaseExecutor baseExecutor, BaseScheduler baseScheduler) {
        this.baseExecutor = baseExecutor;
        this.baseScheduler = baseScheduler;
    }

    public void execute(Subscriber subscriber){

        subscription = buildUseCase().subscribeOn(baseExecutor.scheduler())
                .observeOn(baseScheduler.scheduler())
                .subscribe(subscriber);

    }

    public void unSubscribe(){

        if(subscription != null && !subscription.isUnsubscribed()){

            subscription.unsubscribe();

        }

    }
}
