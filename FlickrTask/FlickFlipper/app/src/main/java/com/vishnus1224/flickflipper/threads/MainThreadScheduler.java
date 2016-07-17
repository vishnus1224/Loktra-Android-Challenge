package com.vishnus1224.flickflipper.threads;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Provides the main thread schedulers to update the ui.
 * Created by Vishnu on 7/16/2016.
 */
public class MainThreadScheduler implements BaseScheduler {

    @Inject
    public MainThreadScheduler() {
    }

    @Override
    public Scheduler scheduler() {
        return AndroidSchedulers.mainThread();
    }
}
