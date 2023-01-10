package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String role);
    Role getById(Long id);
}
