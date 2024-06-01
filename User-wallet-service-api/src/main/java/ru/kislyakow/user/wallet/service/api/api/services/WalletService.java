package ru.kislyakow.user.wallet.service.api.api.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kislyakow.user.wallet.service.api.api.client.TransferProcessingClient;
import ru.kislyakow.user.wallet.service.api.api.converters.WalletDtoConverter;
import ru.kislyakow.user.wallet.service.api.api.dto.TransactionDto;
import ru.kislyakow.user.wallet.service.api.api.dto.WalletDto;
import ru.kislyakow.user.wallet.service.api.api.exceptions.BadRequestException;
import ru.kislyakow.user.wallet.service.api.api.exceptions.NotFoundException;
import ru.kislyakow.user.wallet.service.api.store.entities.UserEntity;
import ru.kislyakow.user.wallet.service.api.store.entities.WalletEntity;
import ru.kislyakow.user.wallet.service.api.store.repository.UserRepository;
import ru.kislyakow.user.wallet.service.api.store.repository.WalletRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final UserRepository userRepository;
    private final WalletDtoConverter walletDtoConverter;
    private final WalletRepository walletRepository;
    private final TransferProcessingClient processingClient;

    public WalletDto createWallet(Long userId, Double amount) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user with such id"));

        if (user.getWallet() != null) throw new BadRequestException("User already has a wallet (id = " + user.getWallet().getId());

        WalletEntity wallet = WalletEntity.builder()
                .balance(amount)
                .build();

        walletRepository.saveAndFlush(wallet);
        user.setWallet(wallet);
        userRepository.saveAndFlush(user);

        return walletDtoConverter.createWalletDto(wallet);
    }

    public Double getWalletBalance(Long id) {
        Double balance = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No wallet with such id"))
                .getBalance();
        return balance;
    }


    @Transactional
    public Boolean updateWalletsBalance(Long senderWallerId, Long recipientWalletId, Double amount) {
        WalletEntity wallet1 = walletRepository.findById(senderWallerId)
                .orElseThrow(() -> new NotFoundException("No such sender wallet"));
        WalletEntity wallet2 = walletRepository.findById(recipientWalletId)
                .orElseThrow(() -> new NotFoundException("No such recipient wallet"));

        wallet1.setBalance(wallet1.getBalance() - amount);
        wallet2.setBalance(wallet2.getBalance() + amount);

        walletRepository.save(wallet1);
        walletRepository.save(wallet2);

        return true;
    }

    public List<TransactionDto> getWalletOperationsHistory(Long id) {
        if (walletRepository.findById(id).isEmpty()) throw new NotFoundException("No such wallet");
        return processingClient.getTransactionHistory(id);
    }
}
