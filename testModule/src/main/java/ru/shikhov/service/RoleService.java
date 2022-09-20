package ru.shikhov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shikhov.model.Role;
import ru.shikhov.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
