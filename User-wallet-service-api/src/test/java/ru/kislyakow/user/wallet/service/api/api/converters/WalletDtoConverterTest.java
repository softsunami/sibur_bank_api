package ru.kislyakow.user.wallet.service.api.api.converters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kislyakow.user.wallet.service.api.api.dto.WalletDto;
import ru.kislyakow.user.wallet.service.api.store.entities.WalletEntity;

class WalletDtoConverterTest {

    private final WalletDtoConverter walletDtoConverter = new WalletDtoConverter();

    @Test
    public void testCorrectWalletDtoConverter() {
        WalletEntity wallet = WalletEntity.builder()
                .id(1L)
                .balance(100D)
                .build();

        WalletDto walletdto = walletDtoConverter.createWalletDto(wallet);

        Assertions.assertEquals(wallet.getId(), walletdto.getId());
        Assertions.assertEquals(wallet.getBalance(), walletdto.getBalance());
    }

    @Test
    public void testEmptyWalletDtoConverter() {
        WalletEntity wallet = new WalletEntity();
        WalletDto walletdto = walletDtoConverter.createWalletDto(wallet);
        Assertions.assertEquals(wallet.getId(), walletdto.getId());
        Assertions.assertEquals(wallet.getBalance(), walletdto.getBalance());
    }
}