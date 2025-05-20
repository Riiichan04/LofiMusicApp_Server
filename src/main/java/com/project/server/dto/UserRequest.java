package com.project.server.dto;

public class UserRequest {
    private Long userId;
    private String name;

    // Constructors
    public UserRequest() {
    }

    public UserRequest(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }
}