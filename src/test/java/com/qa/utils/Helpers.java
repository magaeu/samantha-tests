package com.qa.utils;

import com.qa.dto.UserDTO;

public class Helpers {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String USERS_RESOURCE = "/users";
    public static final String POSTS_RESOURCE = "/posts";
    public static final String COMMENTS_RESOURCE = "/comments";
    public static final String USER_JSON_PATH = "json/users.json";
    public static final String POST_JSON_PATH = "json/post.json";
    public static final String COMMENT_JSON_PATH = "json/comment.json";
    public static final String EXISTING_USER_ID = "3";
    public static final String NON_EXISTING_USER = "11";
    public static final UserDTO EXISTING_USER = new UserDTO()
            .setName("Clementine Bauch")
            .setUsername("Samantha")
            .setEmail("Nathan@yesenia.net");

}
