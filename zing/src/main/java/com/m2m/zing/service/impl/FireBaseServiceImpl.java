package com.m2m.zing.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.m2m.zing.service.FirebaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Service
public class FireBaseServiceImpl implements FirebaseService {

    private final String bucketName = "zing-mp3-2118c.appspot.com";

    public String uploadFileToFirebaseStorage(MultipartFile file) throws IOException {
        try {
            InputStream serviceAccount = Objects.requireNonNull(getClass().getClassLoader()
                    .getResourceAsStream("serviceAccountKey.json"));

            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()
                    .getService();

            BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                    .build();

            storage.create(blobInfo, file.getBytes());

            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new IOException("Failed to upload file: " + e.getMessage());
        }
    }


    public byte[] readFileFromFirebaseStorage(String fileName) throws IOException {
        try {
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");

            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()
                    .getService();

            BlobId blobId = BlobId.of(bucketName, fileName);
            Blob blob = storage.get(blobId);

            if (blob != null && blob.exists()) {
                return blob.getContent();
            } else {
                throw new IOException("File not found on Firebase Storage");
            }
        } catch (StorageException e) {
            throw new IOException("Failed to read file: " + e.getMessage());
        }
    }

}
