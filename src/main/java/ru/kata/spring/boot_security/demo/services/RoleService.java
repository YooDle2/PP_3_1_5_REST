package ru.kata.spring.boot_security.demo.services;

import javassist.NotFoundException;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> listRole();

    void add(Role role);

    void update(Role role);

    Role getById(int id);

    Role getByRole(String role) throws NotFoundException;

    Set<Role> getRoles(String[] role) throws NotFoundException;
}
