package com.project.server.services;

import com.project.server.dto.MusicDTO;
import com.project.server.entity.Music;
import com.project.server.repository.MusicRepository;
import com.project.server.util.MusicFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Service
public class MusicServices {
    private final S3Client s3Client;

    @Autowired
    private MusicRepository musicRepository;
    @Value("${AWS_S3_BUCKET_NAME}")
    private String bucketName;

    public MusicServices(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile musicFile, MultipartFile thumbnailFile, MusicDTO musicDTO) {
        if (!MusicFile.isMp3File(musicFile)) {
            throw new RuntimeException("Invalid music file. Only .mp3 files are accepted.");
        }

        if (thumbnailFile == null || thumbnailFile.isEmpty()) {
            throw new RuntimeException("Thumbnail file cannot be empty.");
        }

        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String musicFileName = timestamp + "_" + musicFile.getOriginalFilename();
            String thumbnailFileName = timestamp + "_" + thumbnailFile.getOriginalFilename();

            String musicKey = "media/music/" + musicFileName;
            String thumbnailKey = "media/thumbnail/" + thumbnailFileName;

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(musicKey)
                            .contentType("audio/mpeg")
                            .build(),
                    RequestBody.fromBytes(musicFile.getBytes())
            );

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(thumbnailKey)
                            .contentType(thumbnailFile.getContentType())
                            .build(),
                    RequestBody.fromBytes(thumbnailFile.getBytes())
            );

            Music music = new Music();
            music.setTitle(musicFile.getOriginalFilename());
            music.setArtist(musicDTO.getArtist());
            music.setGenre(musicDTO.getGenre());
            music.setYear(musicDTO.getYear());
            music.setUrlMusic("https://" + bucketName + ".s3.amazonaws.com/" + musicKey);
            music.setUrlThumbnail("https://" + bucketName + ".s3.amazonaws.com/" + thumbnailKey);

            musicRepository.save(music);
            return "Upload successful";

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload files to S3", e);
        }
    }

    public List<Music> getMusic() {
        return musicRepository.findAll();
    }
}
