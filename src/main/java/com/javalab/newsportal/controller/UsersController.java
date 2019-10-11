package com.javalab.newsportal.controller;

import com.javalab.newsportal.dto.UserDTO;
import com.javalab.newsportal.model.UserRoles;
import com.javalab.newsportal.service.users.UserRegistrationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UsersController {

    private final UserRegistrationService userRegistrationService;

    public UsersController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user, Authentication authentication) {
        userRegistrationService.register(user);
        String result = "redirect:/news/allnews";
        if (authentication.isAuthenticated() && authentication.getAuthorities().contains(UserRoles.ADMIN)) {
            result = "redirect:/admin/users";
        }
        return result;
    }

    @GetMapping("/showForm")
    public String showRegistrationForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }
}
