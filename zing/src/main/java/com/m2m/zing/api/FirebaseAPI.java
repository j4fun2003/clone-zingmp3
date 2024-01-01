package com.m2m.zing.api;

import com.m2m.zing.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/firebase")
public class FirebaseAPI {
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String downloadUrl = firebaseService.uploadFileToFirebaseStorage(file);
            return ResponseEntity.ok("File uploaded to Firebase Storage. Download URL: " + downloadUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

}
