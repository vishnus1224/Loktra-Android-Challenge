package com.vishnus1224.commitsearch.model;

/**
 * Created by Vishnu on 7/16/2016.
 */
public class Author {

    private String name;

    private String email;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Mock author object used for testing.
     * @return
     */
    public static final Author mockAuthor(){

        return new Author("Sam", "Thomson");

    }
}
