package ru.kata.spring.boot_security.demo.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.listUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.listRole());
        return "newUser";
    }

    @PutMapping("/new")
    public String addNewUser(@ModelAttribute User user, @RequestParam("userRoles") String[] role) throws NotFoundException {
        user.setRoles(roleService.getRoles(role));
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getById(id));
        modelMap.addAttribute("role", roleService.listRole());
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam("userRoles") String[] role) throws NotFoundException {
        user.setRoles(roleService.getRoles(role));
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String editUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
