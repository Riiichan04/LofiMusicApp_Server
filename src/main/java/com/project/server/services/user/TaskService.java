package com.project.server.services.user;

import com.project.server.entity.Task;
import com.project.server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public boolean uploadNewTask (Task task) {
        if (task != null) {
            taskRepository.save(task);
            return true;
        }
        return false;
    }
}
