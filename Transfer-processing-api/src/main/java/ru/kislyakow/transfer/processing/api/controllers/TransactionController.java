package ru.kislyakow.transfer.processing.api.controllers;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kislyakow.transfer.processing.api.dto.TransactionDto;
import ru.kislyakow.transfer.processing.api.service.TransactionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class TransactionController {

    private final static String MAKE_MONEY_TRANSFER = "api/processing/transfer";
    private final static String GET_TRANSFER_HISTORY = "api/processing/transfer/{id}";
    private final TransactionService transactionService;

    @PostMapping(MAKE_MONEY_TRANSFER)
    public TransactionDto transferMoney(
            @RequestParam(name = "sender_wallet_id") Long senderWallerId,
            @RequestParam(name = "recipient_wallet_id") Long recipientWalletId,
            @RequestParam @Positive(message = "The payment amount must be greater than zero") Double amount
    ) {
        return transactionService.transferMoney(senderWallerId, recipientWalletId, amount);
    }

    @GetMapping(GET_TRANSFER_HISTORY)
    public List<TransactionDto> getTransactionHistory(
            @PathVariable Long id
    ) {
        return transactionService.getTransactionHistory(id);
    }

}
