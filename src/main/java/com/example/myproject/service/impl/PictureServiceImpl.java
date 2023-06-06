package com.example.myproject.service.impl;

import com.example.myproject.model.entity.OfferEntity;
import com.example.myproject.model.entity.PictureEntity;
import com.example.myproject.model.entity.UserEntity;
import com.example.myproject.repository.PictureRepository;
import com.example.myproject.service.CloudinaryImage;
import com.example.myproject.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public PictureEntity addPicture(PictureEntity picture){
       return pictureRepository.save(picture);
    }

    public PictureEntity saveImageToDB(CloudinaryImage image, UserEntity author, OfferEntity offer) {
        PictureEntity picture = new PictureEntity();
        picture.setPublicId(image.getPublicId());
        picture.setUrl(image.getUrl());
        picture.setAuthor(author);
        picture.setOffer(offer);
        pictureRepository.save(picture);
        return picture;
    }

}
