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

        public String uploadFile(MultipartFile file, MusicDTO musicDTO) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String folderMusic = "media/music/" + fileName;
            String folderThumbnail = "media/thumbnail/" + fileName;
           if (!MusicFile.isMp3File(file)) {
                throw new RuntimeException("Mp3 File Not Found");
           }
            try {
                String contentTypeMusic = "audio/mpeg";
                String contentTypeThumbnail = "image/jpeg";
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(folderMusic)
                                .contentType(contentTypeMusic)
                                .build(),
                        RequestBody.fromBytes(file.getBytes())
                );
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(folderThumbnail)
                                .contentType(contentTypeThumbnail)
                                .build(),
                        RequestBody.fromBytes(file.getBytes())
                );

                Music music = new Music();
                music.setTitle(file.getOriginalFilename());
                music.setArtist(musicDTO.getArtist());
                music.setGenre(musicDTO.getGenre());
                music.setYear(musicDTO.getYear());
                music.setUrlMusic("https://" + bucketName + ".s3.amazonaws.com/" + folderMusic);
                music.setUrlThumbnail("https://" + bucketName + ".s3.amazonaws.com/" + folderThumbnail);

                musicRepository.save(music);
                return "Done";
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file to S3", e);
            }
        }
    }
