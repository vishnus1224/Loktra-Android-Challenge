package com.vishnus1224.flickflipper.threads;

import javax.inject.Inject;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Provides the IO scheduler.
 * Created by Vishnu on 7/16/2016.
 */
public class IoExecutor implements BaseExecutor {

    @Inject
    public IoExecutor() {
    }

    @Override
    public Scheduler scheduler() {
        return Schedulers.io();
    }
}
