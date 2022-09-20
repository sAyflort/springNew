package ru.shikhov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shikhov.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
