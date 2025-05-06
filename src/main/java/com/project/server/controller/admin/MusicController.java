package com.project.server.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.server.dto.MusicDTO;
import com.project.server.services.MusicServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/admin")
public class MusicController {

    private final MusicServices musicServices;

    public MusicController(MusicServices musicServices) {
        this.musicServices = musicServices;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("music") String musicJson ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MusicDTO music = mapper.readValue(musicJson, MusicDTO.class);
        String fileUrl = musicServices.uploadFile(file,music);
        return ResponseEntity.ok("File uploaded successfully: " + fileUrl);
    }

}
