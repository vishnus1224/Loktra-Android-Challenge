package com.vishnus1224.commitsearch.ui.view;

import com.vishnus1224.commitsearch.model.CommitWrapper;

import java.util.List;

/**
 * View abstraction for showing UI to the user.
 * Created by Vishnu on 7/16/2016.
 */
public interface CommitsView {

    void showProgressBar();

    void hideProgressBar();

    void showCommits(List<CommitWrapper> commitWrapperList);
}
