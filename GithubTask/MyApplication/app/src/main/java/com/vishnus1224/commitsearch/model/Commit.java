package com.vishnus1224.commitsearch.model;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class Commit {

    private Author author;
    private String message;

    public Commit(Author author, String message) {
        this.author = author;
        this.message = message;
    }

    public Author getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
