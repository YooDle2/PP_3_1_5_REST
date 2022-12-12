package ru.kata.spring.boot_security.demo.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> listRole() {
        return roleRepository.findAll();
    }

    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getById(int id) {
        Role role = null;
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            role = optionalRole.get();
        }
        return role;
    }

    @Override
    public Role getByRole(String role) throws NotFoundException {
        Role role1 = roleRepository.findByRole(role);
        if (role1 == null) {
            throw new NotFoundException(role);
        }
        return role1;
    }

    @Override
    public Set<Role> getRoles(String[] role) throws NotFoundException {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(getByRole(roles));
        }
        return roleSet;
    }
}
