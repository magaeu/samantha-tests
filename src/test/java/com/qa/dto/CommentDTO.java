package com.qa.dto;

public class CommentDTO {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public int getPostId() {
        return postId;
    }

    public CommentDTO setPostId(int postId) {
        this.postId = postId;
        return this;
    }

    public int getId() {
        return id;
    }

    public CommentDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CommentDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBody() {
        return body;
    }

    public CommentDTO setBody(String body) {
        this.body = body;
        return this;
    }
}
