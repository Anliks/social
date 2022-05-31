package com.example.demo.web;

import com.amazonaws.util.IOUtils;
import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.ImageModel;
import com.example.demo.entity.MusicModel;

import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.repository.MusicRepository;
import com.example.demo.service.MusicUploadService;
import com.example.demo.service.StorageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/music")
@CrossOrigin
public class SongController {

    private final MusicUploadService musicUploadService;
    private final UserService userService;
    private final MusicRepository musicRepository;



    @Autowired
    public SongController(MusicUploadService musicUploadService, UserService userService, MusicRepository musicRepository) {
        this.musicUploadService = musicUploadService;
        this.userService = userService;
        this.musicRepository = musicRepository;
    }

  //  @GetMapping("/musicProfile")
  //  public ResponseEntity<?> getSongs() {
  //      return ResponseEntity.ok(musicRepository.findAll());
  //  }

   // @GetMapping("/musicProfile")
   // public ResponseEntity<MusicModel> getSong(Principal principal) {
   //     MusicModel musicModel = musicUploadService.getMusicToUser(principal);
   //         return new ResponseEntity<>(musicModel, HttpStatus.OK);
   // }
    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadMusicToUser(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        musicUploadService.uploadMusicToUser(file, principal);
        return ResponseEntity.ok(new MessageResponse("music upload successfully"));

    }
      @GetMapping("/musicProfile")
      ResponseEntity<List<MusicModel>> getAllMusicForUser(Principal principal) {
         List<MusicModel> listMusic = musicUploadService.getMusicToUser(principal);
          return new ResponseEntity<>(listMusic, HttpStatus.OK);
      }

      @GetMapping("/delete")
     ResponseEntity<>

    }
