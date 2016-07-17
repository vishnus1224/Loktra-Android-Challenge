package com.vishnus1224.commitsearch.model;

/**
 * Represents the page parameter passed to the query.
 * Created by Vishnu on 7/17/2016.
 */
public class QueryParamPage {

    private final int pageNumber;

    public int getPageNumber() {
        return pageNumber;
    }

    public QueryParamPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Returns the next page by incrementing the page number.
     * @return Next page number for fetching results.
     */
    public QueryParamPage nextPage(){

        int nextPageNumber = pageNumber + 1;

        return new QueryParamPage(nextPageNumber);

    }
}
