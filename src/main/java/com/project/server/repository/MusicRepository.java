package com.project.server.repository;


import com.project.server.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends MongoRepository<Music, Integer> {
    @Override
    <S extends Music> S save(S music);
}
