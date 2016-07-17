package com.vishnus1224.commitsearch.ui.presenter;

import com.vishnus1224.commitsearch.model.QueryParamPage;
import com.vishnus1224.commitsearch.ui.view.CommitsView;
import com.vishnus1224.commitsearch.usecase.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 7/17/2016.
 */
public class CommitsPresenterTest {

    private final int FAKE_PAGE = 445;

    @Mock
    CommitsView commitsView;

    @Mock
    UseCase useCase;

    @Mock
    QueryParamPage queryParamPage;

    private CommitsPresenter commitsPresenter;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        commitsPresenter = new CommitsPresenter(useCase, queryParamPage);

        commitsPresenter.onViewAttached(commitsView);

    }

    @Test
    public void testFetchCommits() throws Exception {

        commitsPresenter.fetchCommits();

        verify(commitsView).showProgressBar();
        verify(useCase).execute(anyInt(), any(Subscriber.class));
    }

    @Test
    public void testLoadCommitsOnNextPage() throws Exception {

        when(queryParamPage.nextPage()).thenReturn(new QueryParamPage(FAKE_PAGE));

        commitsPresenter.loadCommitsOnNextPage();

        verify(queryParamPage).nextPage();
        verify(useCase).execute(anyInt(), any(Subscriber.class));

    }

    @Test
    public void testRetryLoadingCommits(){

        commitsPresenter.retryLoadingCommits();

        verifyZeroInteractions(commitsView);
        verify(commitsView, never()).showProgressBar();
        verify(useCase).execute(anyInt(), any(Subscriber.class));

    }
}