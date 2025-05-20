package com.project.server.controller.admin;


import com.project.server.entity.User;
import com.project.server.services.admin.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("adminUserController")
@RequestMapping("api/admin")
public class UserController {


    @Autowired
    private UserServices userServices;

    @GetMapping("user/findAll")
    public List<User> findAll() {
        return userServices.findAll();
    }
//    @PostMapping("/create")
//    public User create(@RequestBody User user) {
//        return userServices.create(user);
//    }
}
