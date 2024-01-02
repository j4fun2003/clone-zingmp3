package com.m2m.zing.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FirebaseService {
    String uploadFileToFirebaseStorage(MultipartFile file) throws IOException;
}
