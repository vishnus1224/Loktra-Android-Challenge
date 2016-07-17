package com.vishnus1224.commitsearch.usecase;

import com.vishnus1224.commitsearch.threads.BaseExecutor;
import com.vishnus1224.commitsearch.threads.BaseScheduler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class UseCaseTest {

    @Mock
    BaseExecutor executionScheduler;

    @Mock
    BaseScheduler mainThreadScheduler;

    private UseCase useCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        useCase = new TestUseCase(executionScheduler, mainThreadScheduler);
    }

    @Test
    public void testExecute() throws Exception {

        TestSubscriber testSubscriber = new TestSubscriber();

        //create a schedule for testing.
        TestScheduler testScheduler = new TestScheduler();

        when(executionScheduler.scheduler()).thenReturn(testScheduler);

        when(mainThreadScheduler.scheduler()).thenReturn(testScheduler);

        useCase.execute(testSubscriber);

        //check that the size of the events is 0.
        assertThat(testSubscriber.getOnNextEvents().size(), is(0));
    }

    @Test
    public void testUnSubscribe() throws Exception {

        TestSubscriber testSubscriber = new TestSubscriber();

        useCase.execute(testSubscriber);

        useCase.unSubscribe();

        //check if the subscriber has unSubscribed successfully.
        assertThat(testSubscriber.isUnsubscribed(), is(true));

    }

    private final class TestUseCase extends UseCase {

        public TestUseCase(BaseExecutor executionScheduler, BaseScheduler mainThreadScheduler) {
            super(executionScheduler, mainThreadScheduler);
        }

        @Override
        Observable buildUseCase() {
            return Observable.empty();
        }
    }
}