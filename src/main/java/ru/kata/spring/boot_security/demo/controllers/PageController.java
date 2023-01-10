package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class PageController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;

    public PageController(UserRepository userRepository, UserService userService, RoleService roleService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("admin")
    public String adminPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("getAllUsers", userService.getAllUsers());
        model.addAttribute("getAllRoles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("user")
    public String userInfo(Model model, Principal principal) {
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        return "user-page";
    }
}
