package com.m2m.zing.api;

import com.m2m.zing.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/firebase")
public class FirebaseAPI {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String uploadedFileName = firebaseService.uploadFileToFirebaseStorage(file);
            return ResponseEntity.ok("File uploaded to Firebase Storage. Name/Link: " + uploadedFileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<?> handleFileDownload(@PathVariable String fileName) {
        try {
            byte[] fileData = firebaseService.readFileFromFirebaseStorage(fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(fileData);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to download file: " + e.getMessage());
        }
    }
}
