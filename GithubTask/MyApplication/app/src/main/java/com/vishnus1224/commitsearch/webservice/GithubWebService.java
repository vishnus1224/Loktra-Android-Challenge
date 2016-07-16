package com.vishnus1224.commitsearch.webservice;

import com.vishnus1224.commitsearch.model.CommitWrapper;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Represents the github api for fetching data from the server.
 * Created by Vishnu on 7/16/2016.
 */
public interface GithubWebService {

    String BASE_URL = "https://api.github.com/";

    /**
     * Get a list of commits for the repository specified by the url.
     * @return A list of commits.
     */
    @GET("repos/rails/rails/commits")
    Observable<List<CommitWrapper>> getCommits();
}
