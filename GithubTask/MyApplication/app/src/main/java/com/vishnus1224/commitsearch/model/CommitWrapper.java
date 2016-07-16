package com.vishnus1224.commitsearch.model;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class CommitWrapper {

    private String sha;
    private Commit commit;

    public CommitWrapper(String sha, Commit commit) {
        this.sha = sha;
        this.commit = commit;
    }

    public String getSha() {
        return sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public static final CommitWrapper mockCommitWrapper(Commit commit){

        return new CommitWrapper("test", commit);

    }
}
