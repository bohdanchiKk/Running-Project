package com.example.springmvcapp.controller;

import com.example.springmvcapp.dto.RegestrationDto;
import com.example.springmvcapp.models.UserEntity;
import com.example.springmvcapp.service.UserService;
import jakarta.servlet.Registration;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegestrationDto user = new RegestrationDto();
        model.addAttribute("user",user);
        return "register";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegestrationDto user,
                           BindingResult result, Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        UserEntity existingUserUserName = userService.findByUsername(user.getUsername());
        if(existingUserUserName != null && existingUserUserName.getUsername() != null && !existingUserUserName.getUsername().isEmpty()){
            return "redirect:/register?fail";
                    }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}
