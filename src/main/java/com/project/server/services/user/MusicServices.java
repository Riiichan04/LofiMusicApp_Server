package com.project.server.services.user;

import com.project.server.entity.Music;
import com.project.server.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userMusicServices")

public class MusicServices {
    @Autowired
    private MusicRepository musicRepository;

    public List<Music> findByTitle(String keyword) {
        return musicRepository.findByTitleContainingIgnoreCase(keyword);
    }

}
