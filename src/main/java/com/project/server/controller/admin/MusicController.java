package com.project.server.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.server.dto.MusicDTO;
import com.project.server.entity.Music;
import com.project.server.services.admin.MusicServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class MusicController {

    private final MusicServices musicServices;

    public MusicController(MusicServices musicServices) {
        this.musicServices = musicServices;
    }

//1.2. Nhận yêu cầu POST từ client qua endpoint /music/upload chứa
// các tệp nhạc (musicFile), ảnh thumbnail (thumbnailFile) và thông tin nhạc (musicJson).
    @PostMapping("/music/upload")
    public ResponseEntity<String> uploadFile(
            @RequestPart("musicFile") MultipartFile musicFile,
            @RequestPart("thumbnailFile") MultipartFile thumbnailFile,
            @RequestPart("music") String musicJson
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MusicDTO musicDTO = mapper.readValue(musicJson, MusicDTO.class);
        //1.3. Gọi method uploadFile(musicFile, thumbnailFile, musicDTO) của MusicServices
        // để xử lí việc tải tệp lên S3 và lưu thông tin nhạc vào CSDL
        String message = musicServices.uploadFile(musicFile, thumbnailFile, musicDTO);
        return ResponseEntity.ok("File uploaded successfully: " + message);
    }

    @GetMapping("/music/all")
    public ResponseEntity<List<Music>> getAllMusic() {
        List<Music> musicList = musicServices.getMusic();

        if (musicList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musicList);
    }

}
