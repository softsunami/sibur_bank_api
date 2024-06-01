package ru.kislyakow.user.wallet.service.api.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kislyakow.user.wallet.service.api.api.dto.UserDto;
import ru.kislyakow.user.wallet.service.api.store.entities.UserEntity;
import ru.kislyakow.user.wallet.service.api.store.entities.WalletEntity;

class UserDtoConverterTest {

    private final UserDtoConverter userDtoConverter = new UserDtoConverter();

    @Test
    public void testCorrectUserCreateUserDto() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .fullName("Max")
                .email("examplee@mail.com")
                .wallet(new WalletEntity())
                .build();

        UserDto userDto = userDtoConverter.createUserDto(user);

        Assertions.assertEquals(user.getId(), userDto.getId());
        Assertions.assertEquals(user.getFullName(), userDto.getFullName());
        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    void testEmptyUserCreateUserDto() {
        UserEntity user = new UserEntity();
        UserDto userDto = userDtoConverter.createUserDto(user);

        Assertions.assertEquals(user.getId(), userDto.getId());
        Assertions.assertEquals(user.getFullName(), userDto.getFullName());
        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
    }

}