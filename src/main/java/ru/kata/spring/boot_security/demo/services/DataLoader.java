package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {
    RoleService roleService;
    UserService userService;

    @Autowired
    public DataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void loadData() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        roleService.add(role1);
        roleService.add(role2);

        User user1 = new User("admin", "admin", "admin", "admin");
        Set<Role> roles1 = new HashSet<>();
        roles1.add(role1);
        user1.setRoles(roles1);
        userService.saveUser(user1);

        User user2 = new User("user", "user", "user", "user");
        Set<Role> roles2 = new HashSet<>();
        roles2.add(role2);
        user2.setRoles(roles2);
        userService.saveUser(user2);
    }
}
