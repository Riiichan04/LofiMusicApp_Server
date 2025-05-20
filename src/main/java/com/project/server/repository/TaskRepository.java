package com.project.server.repository;

import com.project.server.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, Integer> {
}
