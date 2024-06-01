package ru.kislyakow.user.wallet.service.api.api.converters;

import org.springframework.stereotype.Component;
import ru.kislyakow.user.wallet.service.api.api.dto.UserDto;
import ru.kislyakow.user.wallet.service.api.store.entities.UserEntity;

@Component
public class UserDtoConverter {

    public UserDto createUserDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }

}
