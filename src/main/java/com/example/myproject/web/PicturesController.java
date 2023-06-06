package com.example.myproject.web;

import com.example.myproject.service.CloudinaryService;
import com.example.myproject.service.PictureService;
import org.springframework.stereotype.Controller;

@Controller
public class PicturesController {

    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;


    public PicturesController(CloudinaryService cloudinaryService, PictureService pictureService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
    }

//    @Transactional
//    @DeleteMapping("/pictures/delete")
//    public String delete(@RequestParam("public_id") String publicId) {
//        if (cloudinaryService.delete(publicId)) {
//            pictureRepository.deleteAllByPublicId(publicId);
//        }
//        return "redirect:/pictures/all";
//    }
//
//    public String all(Model model) {
//        List<PictureViewModel> pictures = pictureRepository
//                .findAll().stream().map(p -> asViewModel(p))
//                .collect(Collectors.toList());
//        model.addAttribute("pictures", pictures);
//        return "all";
//    }
//
//    private PictureViewModel asViewModel(PictureEntity pictureEntity) {
//        PictureViewModel pictureViewModel = new PictureViewModel();
//        pictureViewModel.setPublicId(pictureEntity.getPublicId());
//        pictureViewModel.setUrl(pictureEntity.getUrl());
//        return pictureViewModel;
//    }


}
