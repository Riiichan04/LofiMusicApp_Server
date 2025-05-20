package com.project.server.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.UUID;

@Document(collection = "task")
public class Task {
    @Id
    private UUID id = UUID.randomUUID();

    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private Boolean done;
    private UUID userId;


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Boolean getDone() {
        return done;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
