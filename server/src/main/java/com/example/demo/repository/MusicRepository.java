package com.example.demo.repository;

import com.example.demo.entity.MusicModel;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<MusicModel, Long> {

   Optional<MusicModel> findByUserId(Long userId);

   List<MusicModel> findAllByUserId(Long userId);

}
