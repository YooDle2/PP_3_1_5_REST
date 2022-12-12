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
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.add(role1);
        roleService.add(role2);

        User user1 = new User
                ( "admin", "admin","Admin", "Adminov","admin@mail");
        user1.setRoles(Set.of(role1, role2));
        userService.saveUser(user1);

        User user2 = new User
                ( "user", "user","User", "Userov","user@mail");
        Set<Role> roles2 = new HashSet<>();
        user2.setRoles(Set.of(role2));
        userService.saveUser(user2);

        /*User user3 = new User
                ( "user-admin", "user-admin","user-admin", "user-adminov","user-admin@mail");
        Set<Role> roles3 = new HashSet<>();
        roles3.add(role1);
        roles3.add(role2);
        user3.setRoles(roles3);
        userService.saveUser(user3);*/
    }
}
