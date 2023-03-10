package com.example.myproject.web;

import com.example.myproject.model.binding.OfferDTO;
import com.example.myproject.model.entity.UserEntity;
import com.example.myproject.repository.OfferRepository;
import com.example.myproject.service.OfferService;
import com.example.myproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
public class OfferController {

    private final ModelMapper modelMapper;
    private final OfferService offerService;
    private final UserService userService;
    private final OfferRepository offerRepository;


    public OfferController(OfferService offerService, ModelMapper modelMapper, UserService userService, OfferRepository offerRepository) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.offerRepository = offerRepository;
    }

    @GetMapping("/offers/add")
    public String getAddOfferPage(Model model) {

        if (!model.containsAttribute("offerDTO")) {
            model.addAttribute("offerDTO", new OfferDTO());
        }
        model.addAttribute("cities", offerService.getCities());
        model.addAttribute("brands", offerService.getBrands());
        model.addAttribute("models", offerService.getModels());
        return "addOffer";
    }

    @PostMapping("/offers/add")
    public String addOffer (@Valid OfferDTO offerDTO,
                            BindingResult bindingResult,  RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal User user){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("offerDTO", offerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerDTO", bindingResult);
            return "redirect:/offers/add";
        }
//        log.info(offerDTO.getItemCondition());
//        log.info(offerDTO.getBrand());
//        log.info(offerDTO.getCity());
//        log.info(offerDTO.getModel());

       OfferDTO offer  = offerService.addOffer(offerDTO, user.getUsername());
        return "redirect:/offers/" + offer.getId() + "/details";
    }

    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal User currentUser) {

        OfferDTO offerDTO = offerService.findOfferById(id, currentUser.getUsername());
        OfferDTO offerModel = modelMapper.map(offerDTO, OfferDTO.class);
        model.addAttribute("cities", offerService.getCities());
        model.addAttribute("brands", offerService.getBrands());
        model.addAttribute("models", offerService.getModels());
        model.addAttribute("offerModel", offerModel);
        return "editOffer";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal User currentUser) {

        model.addAttribute("cities", offerService.getCities());
        model.addAttribute("brands", offerService.getBrands());
        model.addAttribute("models", offerService.getModels());
        return "editOffer";
    }

    @PatchMapping("/offers/{id}/edit")
    public String patchOffer(
            @PathVariable Long id, @Valid OfferDTO offerDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        OfferDTO offerDto = modelMapper.map(offerDTO, OfferDTO.class);
        offerDto.setId(id);

        offerService.updateOffer(offerDto);

        return "redirect:/offers/" + id + "/details";
    }


    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id,
                              @AuthenticationPrincipal User user){

//        if (!offerService.isOwner(user.getUserIdentifier(), id)) {
//
//        }
        offerService.deleteOffer(id);
        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/details")
    public String showOffer(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal User user){
        UserEntity author = offerRepository.findById(id).get().getAuthor();
        String authorAddress = author.getHomeTown();
        OfferDTO offerById = offerService.findOfferById(id, user.getUsername());
        int commentCounter =  offerRepository.findById(id).get().getComments().size();

        log.info("Zaglavie " + offerById.getTittle());
        log.info("Grad " + offerById.getCity());
        log.info("OPisanie " + offerById.getDescription());
        log.info("curPrice: " + offerById.getPrice());
        log.info("oldPrice: " + offerById.getOldPrice());
        log.info("getCreated: " + instantToString(offerById.getCreated()));
        log.info("getModified: " + instantToString(offerById.getModified()));
        log.info("authorId: " + author.getId());

        model.addAttribute("author", author);
        model.addAttribute("offer", offerById);
        model.addAttribute("commentCounter", commentCounter);
        model.addAttribute("authorAddress", authorAddress);
        model.addAttribute("manufactureYear", offerById.getYear());
        model.addAttribute("createDate", instantToString(offerById.getCreated()));
        if(instantToString(offerById.getCreated()).equals(instantToString(offerById.getModified()))){
            model.addAttribute("modifyDate", null);
        } else {
            model.addAttribute("modifyDate", instantToString(offerById.getModified()));
        }
        return "offerDetails";
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model, String username){
        model.addAttribute("allOffers", offerService.getAllOffers(null));
        return "allOffers";
    }


    private String instantToString(Instant instant) {
        String PATTERN_FORMAT = "dd.MM.yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
