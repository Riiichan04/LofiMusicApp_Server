package com.project.server.controller.user;


import com.project.server.entity.User;
import com.project.server.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserServices userServices;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userServices.findAll();
    }
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userServices.create(user);
    }
}
