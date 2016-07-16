package com.vishnus1224.commitsearch.datastore;

import com.vishnus1224.commitsearch.model.Author;
import com.vishnus1224.commitsearch.model.Commit;
import com.vishnus1224.commitsearch.model.CommitWrapper;
import com.vishnus1224.commitsearch.webservice.GithubWebService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class CloudCommitsDataStoreTest {

    @Mock
    private GithubWebService githubWebService;

    private CloudCommitsDataStore cloudCommitsDataStore;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);

        cloudCommitsDataStore = new CloudCommitsDataStore(githubWebService);


    }

    @Test
    public void testGetCommits() throws Exception {

        //when the web service is called, then return a mock list.
        when(githubWebService.getCommits()).thenReturn(mockCommitList());

        TestSubscriber<List<CommitWrapper>> testSubscriber = new TestSubscriber<>();

        //subscribe to the observable using the test subscriber.
        cloudCommitsDataStore.getCommits().subscribe(testSubscriber);

        //assert that the observable completed.
        testSubscriber.assertCompleted();

        //assert that there were no errors.
        testSubscriber.assertNoErrors();

        //verify that the get commits methods is called on the web service.
        verify(githubWebService).getCommits();

        List<CommitWrapper> commitWrapperList = testSubscriber.getOnNextEvents().get(0);

        CommitWrapper commitWrapper = commitWrapperList.get(0);

        assertTrue(commitWrapper.getSha().equals("test"));
        assertTrue(commitWrapper.getCommit().getAuthor().getName().equals("Sam"));

    }

    private Observable<List<CommitWrapper>> mockCommitList(){

        List<CommitWrapper> commitWrapperList = new ArrayList<>();

        Author author = Author.mockAuthor();

        Commit commit = Commit.mockCommit(author);

        CommitWrapper commitWrapper = CommitWrapper.mockCommitWrapper(commit);

        commitWrapperList.add(commitWrapper);

        return Observable.just(commitWrapperList);

    }
}