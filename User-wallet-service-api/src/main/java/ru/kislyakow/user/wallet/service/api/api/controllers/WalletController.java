package ru.kislyakow.user.wallet.service.api.api.controllers;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kislyakow.user.wallet.service.api.api.dto.TransactionDto;
import ru.kislyakow.user.wallet.service.api.api.dto.WalletDto;
import ru.kislyakow.user.wallet.service.api.api.services.WalletService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class WalletController {

    private final WalletService walletService;

    private static final String CREATE_WALLET = "api/wallets";
    private static final String GET_WALLET_BALANCE_BY_ID = "api/wallets/{id}/balance";
    private static final String UPDATE_WALLETS_BALANCE = "api/wallets/transfer";
    private static final String WALLET_OPERATIONS_HISTORY = "api/wallets/{id}/history";

    @PostMapping(CREATE_WALLET)
    public @ResponseBody WalletDto createWallet(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam @PositiveOrZero(message = "The initial wallet balance cannot be less than zero") Double balance
    ) {
        return walletService.createWallet(userId, balance);
    }

    @GetMapping(GET_WALLET_BALANCE_BY_ID)
    public Double getWalletBalance(
            @PathVariable Long id
    ) {
        return walletService.getWalletBalance(id);
    }

    @PostMapping(UPDATE_WALLETS_BALANCE)
    public Boolean updateWalletsBalance(
            @RequestParam(name = "sender_wallet_id") Long senderWallerId,
            @RequestParam(name = "recipient_wallet_id") Long recipientWalletId,
            @RequestParam @Positive(message = "The payment amount must be greater than zero") Double amount) {
        return walletService.updateWalletsBalance(senderWallerId, recipientWalletId, amount);
    }

    @GetMapping(WALLET_OPERATIONS_HISTORY)
    public List<TransactionDto> getWalletOperationsHistory(
            @PathVariable Long id
    ) {
        return walletService.getWalletOperationsHistory(id);
    }
}