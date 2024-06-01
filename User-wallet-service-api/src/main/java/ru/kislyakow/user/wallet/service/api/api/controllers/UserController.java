package ru.kislyakow.user.wallet.service.api.api.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kislyakow.user.wallet.service.api.api.dto.UserDto;
import ru.kislyakow.user.wallet.service.api.api.services.UserService;

@RequiredArgsConstructor
@RestController
@Validated
public class UserController {
    private final UserService userService;

    private static final String CREATE_USER = "api/users";
    private static final String UPDATE_USER_BY_ID = "api/users/{id}";

    @PostMapping(CREATE_USER)
    public @ResponseBody UserDto createUser(
            @RequestParam(name = "full_name") @NotBlank(message = "Full name cannot be empty") String fullName,
            @RequestParam @Email(message = "Incorrect email") String email
    ) {
        return userService.createUser(fullName, email);
    }

    @PatchMapping(UPDATE_USER_BY_ID)
    public @ResponseBody UserDto updateUser(
            @PathVariable Long id,
            @RequestParam(name = "full_name", required = false) String fullName,
            @RequestParam(required = false) @Email(message = "Incorrect email") String email
    ) {
        return userService.updateUser(id, fullName, email);
    }

}