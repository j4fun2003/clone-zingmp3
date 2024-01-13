package com.m2m.zing.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AlbumDTO {
    private String title;
    private String description;
    private MultipartFile selectImg;
    private Long selectAuthor;

}