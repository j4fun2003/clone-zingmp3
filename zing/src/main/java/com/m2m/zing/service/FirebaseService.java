package com.m2m.zing.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FirebaseService {
    String uploadFileToFirebaseStorage(MultipartFile file) throws IOException;

    // Hàm đọc tệp từ Firebase Storage dựa trên đường dẫn đã upload và trả về dữ liệu tệp
    byte[] readFileFromFirebaseStorage(String fileUrl) throws IOException;
}
