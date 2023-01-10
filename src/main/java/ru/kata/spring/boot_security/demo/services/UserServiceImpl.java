package ru.kata.spring.boot_security.demo.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.MyPasswordEncoder;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MyPasswordEncoder myPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, MyPasswordEncoder myPasswordEncoder) {
        this.userRepository = userRepository;
        this.myPasswordEncoder = myPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(myPasswordEncoder.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void update(User user) {
        user.setPassword(myPasswordEncoder.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getByName(String userName) throws NotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new NotFoundException(userName);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException(email);
        }
        return user;
    }
}
