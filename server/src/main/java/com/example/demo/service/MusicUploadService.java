package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.repository.MusicRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class MusicUploadService {

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${find.path}")
    private String findPath;

    private MusicRepository musicRepository;
    private UserRepository userRepository;

    @Autowired
    public MusicUploadService(MusicRepository musicRepository, UserRepository userRepository) {
        this.musicRepository = musicRepository;
        this.userRepository = userRepository;
    }
public MusicModel uploadMusicToUser(MultipartFile file, Principal principal) throws IOException {
        User user = getUserByPrincipal(principal);
     if(file == null) {
      return null;
     }
     MusicModel musicModel = new MusicModel();
     musicModel.setUserId(user.getId());
     musicModel.setTitle(file.getOriginalFilename());
     musicModel.setFileName(findPath + "/" + file.getOriginalFilename());
     File uploadDir = new File(uploadPath);
     if(file != null) {
         if(!uploadDir.exists()) {
             uploadDir.mkdir();
         }
         file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
     }
     return musicRepository.save(musicModel);
}

    public List<MusicModel> getMusicToUser(Principal principal) {
        Long userId = getUserByPrincipal(principal).getId();
        List<MusicModel> musicModel = musicRepository.findAllByUserId(userId);
        return musicModel;
    }


    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }
}
