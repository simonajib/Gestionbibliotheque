package com.example.gestionbibliotheque.entities;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Image {
    public static void saveImage(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        File file = new File(uploadDir + fileName);
        multipartFile.transferTo(file);
    }
}
