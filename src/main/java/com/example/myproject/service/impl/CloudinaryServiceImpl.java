package com.example.myproject.service.impl;

import com.cloudinary.Cloudinary;
import com.example.myproject.model.entity.OfferEntity;
import com.example.myproject.model.entity.PictureEntity;
import com.example.myproject.model.entity.UserEntity;
import com.example.myproject.service.CloudinaryImage;
import com.example.myproject.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    
    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.
                    uploader().upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL,
                    "https://thumbs.dreamstime.com/b/no-image-available-icon-photo-camera-flat-vector-illustration-132483141.jpg");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            CloudinaryImage cloudinaryImage = new CloudinaryImage();
            cloudinaryImage.setPublicId(publicId);
            cloudinaryImage.setUrl(url);
            return cloudinaryImage;
        } finally {
            tempFile.delete();
        }
    }


    @Override
    public boolean delete(String publicId) {
        try{
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e){
            return false;
        }
        return true;
    }
}
