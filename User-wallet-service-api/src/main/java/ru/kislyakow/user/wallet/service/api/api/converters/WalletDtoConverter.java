package ru.kislyakow.user.wallet.service.api.api.converters;

import org.springframework.stereotype.Component;
import ru.kislyakow.user.wallet.service.api.api.dto.WalletDto;
import ru.kislyakow.user.wallet.service.api.store.entities.WalletEntity;

@Component
public class WalletDtoConverter {

    public WalletDto createWalletDto(WalletEntity wallet) {
        return WalletDto.builder()
                .id(wallet.getId())
                .balance(wallet.getBalance())
                .build();
    }
}
