package com.example.myproject.web;

import com.example.myproject.model.binding.UserDTO;
import com.example.myproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userDTO")
    public UserDTO userModel() {
        return new UserDTO();
    }

    @GetMapping("/users/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/users/register")
    public String register(
            @Valid UserDTO userDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);

            return "redirect:/users/register";
        }

        userService.registerAndLoginUser(userDTO);

        return "redirect:/";
    }

}
