package ru.shikhov.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.shikhov.exceptions.EntityNotFoundException;
import ru.shikhov.model.dto.UserDto;
import ru.shikhov.service.UserService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService service;

    @GetMapping
    public Page<UserDto> listPage(
            @RequestParam(required = false) String usernameFilter,
            @RequestParam(required = false) String emailFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false) Optional<String> sortField
    ) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        Page<UserDto> allByFilter = service.findAllByFilter(usernameFilter, emailFilter, pageValue, sizeValue, sortFieldValue);
        return allByFilter;
    }

    @GetMapping("/{id}/id")
    public UserDto form(@PathVariable("id") long id, Model model) {
        return service.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto user) {
        try {
            service.save(user);
        } catch (RuntimeException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return user;
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Created user shouldn't have id");
        }
        service.save(user);
        return user;
    }


}
