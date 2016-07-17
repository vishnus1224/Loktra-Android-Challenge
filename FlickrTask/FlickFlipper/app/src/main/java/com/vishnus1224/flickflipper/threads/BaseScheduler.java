package com.vishnus1224.flickflipper.threads;

import rx.Scheduler;

/**
 * Abstraction for providing various schedulers to carry out work on different threads.
 * Subclasses will provide the required scheduler.
 * Created by Vishnu on 7/16/2016.
 */
public interface BaseScheduler {

    Scheduler scheduler();

}
