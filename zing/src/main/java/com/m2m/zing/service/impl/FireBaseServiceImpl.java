package com.m2m.zing.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.m2m.zing.service.FirebaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class FireBaseServiceImpl implements FirebaseService {

    private final String bucketName = "zing-mp3-2118c.appspot.com";
    @Override
    public String uploadFileToFirebaseStorage(MultipartFile file) throws IOException {
        try {
            InputStream serviceAccount = Objects.requireNonNull(getClass().getClassLoader()
                    .getResourceAsStream("serviceAccountKey.json")); // Đường dẫn đến Firebase Admin SDK JSON file

            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()
                    .getService();

            BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Blob blob = storage.create(blobInfo, file.getBytes());
            return blob.getMediaLink(); // Trả về URL để tải xuống tệp vừa tải lên
        } catch (IOException e) {
            throw new IOException("Failed to upload file: " + e.getMessage());
        }
    }
}
