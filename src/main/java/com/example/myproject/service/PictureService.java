package com.example.myproject.service;

import com.example.myproject.model.entity.OfferEntity;
import com.example.myproject.model.entity.PictureEntity;
import com.example.myproject.model.entity.UserEntity;

import java.util.List;

public interface PictureService {

    PictureEntity addPicture(PictureEntity picture);
    PictureEntity saveImageToDB(CloudinaryImage image, UserEntity author, OfferEntity offer);
}
