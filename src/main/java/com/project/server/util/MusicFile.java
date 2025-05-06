package com.project.server.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MusicFile {


    public static boolean isMp3File(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            if (bytes.length < 3) return false;

            // Kiểm tra ID3 header (metadata của MP3)
            if (bytes[0] == 'I' && bytes[1] == 'D' && bytes[2] == '3') {
                return true;
            }

            // Kiểm tra frame sync MP3: byte đầu tiên 0xFF, byte thứ hai bắt đầu bằng 0xF
            if ((bytes[0] & 0xFF) == 0xFF && (bytes[1] & 0xE0) == 0xE0) {
                return true;
            }

        } catch (IOException e) {
            return false;
        }

        return false;
    }



}
