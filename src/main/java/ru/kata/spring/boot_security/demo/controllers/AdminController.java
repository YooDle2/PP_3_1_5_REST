package ru.kata.spring.boot_security.demo.controllers;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
//        List<User> allUsers = userService.listUsers();
        model.addAttribute("allUsers", userService.listUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.listRole());
        return "user-info";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user, @RequestParam("roles") String[] role) throws NotFoundException {
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

    @PutMapping("/edit/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam("roles") String[] role) throws NotFoundException {
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
