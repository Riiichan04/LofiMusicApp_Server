package com.project.server.controller.user;


import com.project.server.entity.Music;
import com.project.server.services.user.MusicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userMusicController")
@RequestMapping("/api/user")
public class MusicController {
    @Autowired
    private MusicServices musicServices;


    @GetMapping("/music/search")
    public ResponseEntity<List<Music>>findMusic(@RequestParam("keyword")String keyword) {
        return ResponseEntity.ok(musicServices.findByTitle(keyword)) ;
    }

}
