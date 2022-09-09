package ru.shikhov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shikhov.exceptions.EntityNotFoundException;
import ru.shikhov.model.User;
import ru.shikhov.repository.UserRepository;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    /*@GetMapping
    public String listPage(@RequestParam Optional<String> usernameFilter, Model model) {
        if (usernameFilter.isEmpty() || usernameFilter.get().isBlank()) {
            model.addAttribute("users", userRepository.findAll());
        } else {
            model.addAttribute("users", userRepository.usersByUsername("%" + usernameFilter.get() + "%"));
        }
        return "user";
    }*/

    @GetMapping
    public String listPage(
            @RequestParam(required = false) String usernameFilter,
            Model model
    ) {
        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
        model.addAttribute("users", userRepository.usersByFilter(usernameFilter));
        return "user";
    }


    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        Model model1 = model.addAttribute("user", userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        return "user_form";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User(""));
        return "user_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

}
