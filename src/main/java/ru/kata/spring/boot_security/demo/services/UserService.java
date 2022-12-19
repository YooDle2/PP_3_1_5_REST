package ru.kata.spring.boot_security.demo.services;


import javassist.NotFoundException;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    void saveUser(User user);

    User getById(int id);

    void update(User user);

    void delete(int id);

    User getByUsername(String username) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;
}
