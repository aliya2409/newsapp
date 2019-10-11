package com.javalab.newsportal.controller;

import com.javalab.newsportal.annotations.security.IsAdmin;
import com.javalab.newsportal.dto.AuthoritiesDTO;
import com.javalab.newsportal.model.User;
import com.javalab.newsportal.service.users.AllUsersRetrievalService;
import com.javalab.newsportal.service.users.UserAuthoritiesAssignmentService;
import com.javalab.newsportal.service.users.UserRemovalService;
import com.javalab.newsportal.service.users.UserRetrievalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("admin")
@IsAdmin
public class AdminController {

    private final UserRemovalService userRemovalService;
    private final AllUsersRetrievalService allUsersRetrievalService;
    private final UserAuthoritiesAssignmentService userAuthoritiesAssignmentService;
    private final UserRetrievalService userRetrievalService;

    public AdminController(UserRemovalService userRemovalService, AllUsersRetrievalService allUsersRetrievalService, UserAuthoritiesAssignmentService userAuthoritiesAssignmentService, UserRetrievalService userRetrievalService) {
        this.userRemovalService = userRemovalService;
        this.allUsersRetrievalService = allUsersRetrievalService;
        this.userAuthoritiesAssignmentService = userAuthoritiesAssignmentService;
        this.userRetrievalService = userRetrievalService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        List<User> users = allUsersRetrievalService.retrieveAll();
        model.addAttribute("userList", users);
        model.addAttribute("authorities", new AuthoritiesDTO(new HashSet<>()));
        return "users";
    }

    @PostMapping("/users/delete/{id}")
    public String delete(@PathVariable Long id) {
        userRemovalService.removeById(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/authorities")
    public String assignAuthorities(@ModelAttribute AuthoritiesDTO authorities, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "index";
        }
        User user = userRetrievalService.retrieve(id);
        userAuthoritiesAssignmentService.assignAuthorities(user, authorities.getRoles());
        return "redirect:/admin/users";
    }
}
