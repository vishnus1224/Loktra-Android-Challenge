package com.vishnus1224.flickflipper.usecase;

import com.vishnus1224.flickflipper.threads.BaseExecutor;
import com.vishnus1224.flickflipper.threads.BaseScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class UseCaseTest {

    @Mock
    BaseExecutor baseExecutor;

    @Mock
    BaseScheduler baseScheduler;

    private UseCase useCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        useCase = new TestUseCase(baseExecutor, baseScheduler);

    }

    @Test
    public void testExecute() throws Exception {

        TestScheduler testScheduler = new TestScheduler();

        when(baseExecutor.scheduler()).thenReturn(testScheduler);

        when(baseScheduler.scheduler()).thenReturn(testScheduler);

        TestSubscriber testSubscriber = new TestSubscriber();

        useCase.execute(testSubscriber);

        testSubscriber.assertNoValues();

        assertEquals(testSubscriber.getOnNextEvents().size(), 0);

    }

    @Test
    public void testUnSubscribe() throws Exception {

        TestSubscriber testSubscriber = new TestSubscriber();

        useCase.execute(testSubscriber);

        useCase.unSubscribe();

        assertEquals(testSubscriber.isUnsubscribed(), true);

    }

    private final class TestUseCase extends UseCase{

        public TestUseCase(BaseExecutor baseExecutor, BaseScheduler baseScheduler) {
            super(baseExecutor, baseScheduler);
        }

        @Override
        Observable buildUseCase() {
            return Observable.empty();
        }
    }
}