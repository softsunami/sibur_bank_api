package ru.kislyakow.user.wallet.service.api.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kislyakow.user.wallet.service.api.api.converters.UserDtoConverter;
import ru.kislyakow.user.wallet.service.api.api.dto.UserDto;
import ru.kislyakow.user.wallet.service.api.api.exceptions.BadRequestException;
import ru.kislyakow.user.wallet.service.api.api.exceptions.NotFoundException;
import ru.kislyakow.user.wallet.service.api.store.entities.UserEntity;
import ru.kislyakow.user.wallet.service.api.store.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserDto createUser(String fullName, String email) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new BadRequestException(String.format("User with email \"%s\" already exists", email));
                });

        UserEntity user = UserEntity.builder()
                .fullName(fullName)
                .email(email)
                .build();

        userRepository.saveAndFlush(user);
        return userDtoConverter.createUserDto(user);
    }

    public UserDto updateUser(Long id, String fullName, String email) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user with such id"));

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException(String.format("User with email \"%s\" already exists", email));
        }
        if (fullName != null) user.setFullName(fullName);
        if (email != null) user.setEmail(email);
        userRepository.save(user);
        return userDtoConverter.createUserDto(user);
    }
}
