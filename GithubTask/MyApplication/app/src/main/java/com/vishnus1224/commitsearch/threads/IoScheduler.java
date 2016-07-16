package com.vishnus1224.commitsearch.threads;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Provides the IO scheduler.
 * Created by Vishnu on 7/16/2016.
 */
public class IoScheduler implements BaseScheduler {

    @Override
    public Scheduler scheduler() {
        return Schedulers.io();
    }
}
