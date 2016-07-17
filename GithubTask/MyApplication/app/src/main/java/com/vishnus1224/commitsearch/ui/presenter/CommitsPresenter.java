package com.vishnus1224.commitsearch.ui.presenter;

import com.vishnus1224.commitsearch.model.CommitWrapper;
import com.vishnus1224.commitsearch.model.QueryParamPage;
import com.vishnus1224.commitsearch.ui.view.CommitsView;
import com.vishnus1224.commitsearch.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Handles all the operations related to the commits activity.
 * All interactions on the view by the user will be passed
 * to the presenter to take correct actions.
 * Created by Vishnu on 7/16/2016.
 */
public class CommitsPresenter {

    private CommitsView commitsView;

    private UseCase useCase;

    private QueryParamPage queryParamPage;

    @Inject
    public CommitsPresenter(@Named("getCommits") UseCase useCase, QueryParamPage queryParamPage) {

        this.useCase = useCase;

        this.queryParamPage = queryParamPage;

    }

    public void onViewAttached(CommitsView commitsView){

        this.commitsView = commitsView;

    }

    public void onViewDetached(){

        useCase.unSubscribe();

    }

    public void fetchCommits(){

        commitsView.showProgressBar();

        useCase.execute(queryParamPage.getPageNumber(), new CommitWrapperSubscriber());

    }

    public void loadCommitsOnNextPage(){

        queryParamPage = queryParamPage.nextPage();

        useCase.execute(queryParamPage.getPageNumber(), new CommitWrapperSubscriber());

    }

    public void retryLoadingCommits(){

        //show the progress bar when loading results for the first time.
        if(queryParamPage.getPageNumber() == 1){

            commitsView.showProgressBar();

        }

        useCase.execute(queryParamPage.getPageNumber(), new CommitWrapperSubscriber());

    }

    private final class CommitWrapperSubscriber extends Subscriber<List<CommitWrapper>> {


        @Override
        public void onCompleted() {

            commitsView.hideProgressBar();

        }

        @Override
        public void onError(Throwable e) {

            commitsView.hideProgressBar();

            commitsView.showError("An error occurred");

        }

        @Override
        public void onNext(List<CommitWrapper> commitWrapperList) {

            commitsView.showCommits(commitWrapperList);

        }
    }

}
