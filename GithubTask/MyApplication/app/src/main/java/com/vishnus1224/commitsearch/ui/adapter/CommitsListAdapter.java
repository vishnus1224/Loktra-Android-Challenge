package com.vishnus1224.commitsearch.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vishnus1224.commitsearch.R;
import com.vishnus1224.commitsearch.model.CommitWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class CommitsListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<CommitWrapper> commitWrapperList = new ArrayList<>();

    @Inject
    public CommitsListAdapter(LayoutInflater layoutInflater){

        this.layoutInflater = layoutInflater;

    }

    public void updateDataSet(List<CommitWrapper> commitWrapperList){

        this.commitWrapperList.clear();

        this.commitWrapperList.addAll(commitWrapperList);

        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return commitWrapperList.size();
    }

    @Override
    public Object getItem(int i) {
        return commitWrapperList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommitsViewHolder commitsViewHolder;

        if(view == null){

            view = layoutInflater.inflate(R.layout.adapter_commits_list, viewGroup, false);

            commitsViewHolder = new CommitsViewHolder();

            commitsViewHolder.authorNameTextView = (TextView) view.findViewById(R.id.adapterCommitsAuthor);
            commitsViewHolder.commitShaTextView = (TextView) view.findViewById(R.id.adapterCommitsSha);
            commitsViewHolder.commitMessageTextView = (TextView) view.findViewById(R.id.adapterCommitsMessage);

            view.setTag(commitsViewHolder);

        }else{

            commitsViewHolder = (CommitsViewHolder) view.getTag();

        }

        //set data on the views.

        CommitWrapper commitWrapper = (CommitWrapper) getItem(i);

        commitsViewHolder.authorNameTextView.setText(commitWrapper.getCommit().getAuthor().getName());

        commitsViewHolder.commitShaTextView.setText(commitWrapper.getSha());

        commitsViewHolder.commitMessageTextView.setText(commitWrapper.getCommit().getMessage());

        return view;
    }

    private static class CommitsViewHolder{

        TextView authorNameTextView;
        TextView commitShaTextView;
        TextView commitMessageTextView;

    }
}
