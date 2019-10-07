package com.qa.utils;

import com.qa.dto.PostDTO;
import com.qa.dto.UserDTO;

public class Helpers {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String USERS_RESOURCE = "/users";
    public static final String USER_ID_PARAM = "userId";
    public static final String POSTS_RESOURCE = "/posts";
    public static final String POSTS_USER_ID_RESOURCE = "/posts?userId=";
    public static final String COMMENTS_RESOURCE = "/comments";
    public static final String USERS_JSON_PATH = "json/users.json";
    public static final String POSTS_JSON_PATH = "json/posts.json";
    public static final String COMMENTS_JSON_PATH = "json/comments.json";
    public static final String EXISTING_USER_ID = "3";
    public static final String NON_EXISTING_USER = "11";
    public static final String EXISTING_POST_ID = "21";
    public static final String NON_EXISTING_POST = "101";
    public static final UserDTO EXISTING_USER = new UserDTO()
            .setName("Clementine Bauch")
            .setUsername("Samantha")
            .setEmail("Nathan@yesenia.net");
    public static final PostDTO EXISTING_POST = new PostDTO()
            .setUserId(3)
            .setId(21)
            .setTitle("asperiores ea ipsam voluptatibus modi minima quia sint")
            .setBody("repellat aliquid praesentium dolorem quo\nsed totam minus non itaque\nnihil " +
                    "labore molestiae sunt dolor eveniet hic recusandae veniam\ntempora et tenetur expedita sunt");

}
