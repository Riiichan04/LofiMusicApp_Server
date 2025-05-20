package com.project.server.controller.user;

import com.project.server.dto.UserRequest;
import com.project.server.entity.Task;
import com.project.server.repository.TaskRepository;
import com.project.server.repository.UserRepository;
import com.project.server.services.user.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/user")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create/task")
    public ResponseEntity<?> getUser(@RequestBody Task task) {
        if (taskService.uploadNewTask(task)) {
            return ResponseEntity.ok("Upload Success");
        }
        return ResponseEntity.ok("Upload Failed");
    }
    }
