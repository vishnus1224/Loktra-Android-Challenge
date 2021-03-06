package com.vishnus1224.commitsearch.usecase;

import com.vishnus1224.commitsearch.repository.CommitsRepository;
import com.vishnus1224.commitsearch.threads.BaseExecutor;
import com.vishnus1224.commitsearch.threads.BaseScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class GetCommitsUseCaseTest {

    @Mock
    BaseExecutor ioExecutor;

    @Mock
    BaseScheduler mainThreadScheduler;

    @Mock
    CommitsRepository commitsRepository;

    private GetCommitsUseCase getCommitsUseCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        getCommitsUseCase = new GetCommitsUseCase(commitsRepository, ioExecutor, mainThreadScheduler);
    }

    @Test
    public void testBuildUseCase() throws Exception {

        getCommitsUseCase
                .buildUseCase(anyInt());

        verify(commitsRepository).getCommits(anyInt());

        //verify no more interactions are happening with the repository.
        verifyNoMoreInteractions(commitsRepository);

        //verify that no interactions happened on the io thread mock.
        verifyZeroInteractions(ioExecutor);

        //verify that no interactions happened on the main thread mock.
        verifyZeroInteractions(mainThreadScheduler);

    }
}