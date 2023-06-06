package com.example.myproject.service;

import com.example.myproject.model.binding.OfferDTO;
import com.example.myproject.model.entity.BrandEntity;
import com.example.myproject.model.entity.CityEntity;
import com.example.myproject.model.entity.ModelEntity;
import com.example.myproject.model.entity.OfferEntity;

import java.util.List;

public interface OfferService {

    // void initializeOffers();

    List<OfferDTO> getAllOffers(String currentUser);

    List<OfferDTO> getByKeyword(String keyword);

    OfferDTO findOfferById(Long id, String currentUser);
    OfferEntity findOfferEntityById(Long id);

    void deleteOffer(Long id);

    boolean hasPrivileges(String userName, Long id);

    void updateOffer(OfferDTO offerDTO);

    OfferDTO addOffer(OfferDTO offerDTO, String ownerId, String imageUrl);

    List<OfferEntity> getAllByAuthor(Long authorId);

    List<CityEntity> getCities();

    List<BrandEntity> getBrands();

    List<ModelEntity> getModels();
}
