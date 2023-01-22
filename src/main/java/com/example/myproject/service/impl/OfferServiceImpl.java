package com.example.myproject.service.impl;

import com.example.myproject.model.binding.OfferDTO;
import com.example.myproject.model.entity.*;
import com.example.myproject.repository.*;
import com.example.myproject.service.OfferService;
import com.example.myproject.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserRepository userRepository, CityRepository cityRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }


    @Override
    public List<OfferEntity> getAllByAuthor(Long authorId) {
        return offerRepository.getAllByAuthor(authorId)
                .stream().map(o -> modelMapper.map(o, OfferEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OfferDTO> getByKeyword(String keyword) {
        return offerRepository.getAllByName(keyword.toUpperCase())
                .stream().map(o -> modelMapper.map(o, OfferDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public List<OfferDTO> getAllOffers(String currentUser) {
        return offerRepository.
                findAllOffers().
                stream().
                map(o -> mapToOfferDTO(null, o)).
                collect(Collectors.toList());
    }

    @Override
    public OfferDTO findOfferById(Long id, String currentUser) {
        return  offerRepository.
                findById(id).
                map(o -> mapToOfferDTO(currentUser, o))
                .get();

    }
    private OfferDTO mapToOfferDTO(String currentUser, OfferEntity offer) {

        return OfferDTO.builder()
                .id(offer.getId())
                .tittle(offer.getTittle())
                .imageUrl(offer.getImageUrl())
                .price(offer.getPrice())
                .description(offer.getDescription())
                .itemCondition(offer.getItemCondition())
                .brand(offer.getBrand())
                .model(offer.getModel())
                .city(offer.getCity())
                .year(offer.getYear())
                .pictures(offer.getPictures())
                .canDelete(hasPrivileges(currentUser, offer.getId()))
                .sellerFullName(offer.getAuthor().getFirstName() + " " + offer.getAuthor().getLastName())
                .oldPrice(offer.getOldPrice())
                .build();
    }

    public boolean hasPrivileges(String userName, Long offerId) {
        Optional<OfferEntity> offer = offerRepository.
                findById(offerId);
        Optional<UserEntity> user = userRepository.
                findByUsername(userName);

        if (offer.isEmpty() || user.isEmpty()) {
            return false;
        } else {
            OfferEntity offerEntity = offer.get();

            return isAdmin(user.get()) ||
                    offerEntity.getAuthor().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }


    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferDTO offerModel) {

        OfferEntity offerEntity  =offerRepository.findById(offerModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("There is no offer with id: " + offerModel.getId()));

        offerEntity.setTittle(offerModel.getTittle());
        offerEntity.setItemCondition(offerModel.getItemCondition());
        offerEntity.setCity(offerModel.getCity());
        offerEntity.setBrand(offerModel.getBrand());
        offerEntity.setModel(offerModel.getModel());
        offerEntity.setYear(offerModel.getYear());
        offerEntity.setDescription(offerModel.getDescription());
        offerEntity.setImageUrl(offerModel.getImageUrl());

        int isPriceChanged = Double.compare(offerEntity.getPrice(), offerModel.getPrice());
        if (isPriceChanged != 0) {
            offerEntity.setOldPrice(offerEntity.getPrice());
        }
        offerEntity.setPrice(offerModel.getPrice());

        offerRepository.save(offerEntity);
    }

    @Override
    public OfferDTO addOffer(OfferDTO offerDTO, String username) {
       UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        OfferEntity offerEntity = modelMapper.map(offerDTO, OfferEntity.class);
       if(offerDTO.getImageUrl().isEmpty()) {
           offerEntity.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPZGfs_m5LuvZI7FK6y2EclXvkh1htO5LwmkasO7KdNPC1eSLbdhqJpWWryT40Xwokjfg&usqp=CAU");
       }
     //   offerEntity.setCreated(Instant.now());
        offerEntity.setAuthor(userEntity);

        OfferEntity offer = offerRepository.save(offerEntity);
        return modelMapper.map(offer, OfferDTO.class);
    }

    public List<CityEntity> getCities(){
        return cityRepository.findAll();
    }

    @Override
    public List<BrandEntity> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<ModelEntity> getModels() {
        return modelRepository.findAll();
    }

}
