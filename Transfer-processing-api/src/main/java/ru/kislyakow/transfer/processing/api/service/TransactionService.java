package ru.kislyakow.transfer.processing.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kislyakow.transfer.processing.api.client.UserWalletsClient;
import ru.kislyakow.transfer.processing.api.converters.TransactionDtoConverter;
import ru.kislyakow.transfer.processing.api.dto.TransactionDto;
import ru.kislyakow.transfer.processing.api.exceptions.BadRequestException;
import ru.kislyakow.transfer.processing.api.exceptions.NotFoundException;
import ru.kislyakow.transfer.processing.store.entity.TransactionEntity;
import ru.kislyakow.transfer.processing.store.repositoty.TransactionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final UserWalletsClient userWalletsClient;
    private final WalletFreezeService walletFreezeService;
    private final TransactionRepository transactionRepository;
    private final TransactionDtoConverter transactionDtoConverter;

    public TransactionDto transferMoney(Long senderWalletId, Long recipientWalletId, Double amount) {
        validateTransferParams(senderWalletId, recipientWalletId, amount);

        walletFreezeService.frozeWallet(senderWalletId);
        try {
            TransactionEntity transaction = createTransaction(senderWalletId, recipientWalletId, amount);
            transactionRepository.saveAndFlush(transaction);

            userWalletsClient.updateWalletsBalance(senderWalletId, recipientWalletId, amount);
            transaction.setSuccessfulState(true);
            transactionRepository.save(transaction);
            return transactionDtoConverter.createTransactionDto(transaction);
        } finally {
            walletFreezeService.unfrozeWallet(senderWalletId);
        }
    }

    private void validateTransferParams(Long senderWalletId, Long recipientWalletId, Double amount) {
        if (senderWalletId.equals(recipientWalletId)) {
            throw new BadRequestException("Sender and recipient wallet's id can't be equal");
        }

        if (walletFreezeService.isWalletFrozen(senderWalletId)) {
            throw new BadRequestException("Another sender's transaction is currently being processed");
        }

        Double senderWalletBalance = userWalletsClient.getWalletBalance(senderWalletId)
                .orElseThrow(() -> new NotFoundException("No such sender wallet"));
        if (senderWalletBalance < amount) throw new BadRequestException("The sender's wallet does not have enough funds");

        if (userWalletsClient.getWalletBalance(recipientWalletId).isEmpty()) {
            throw new NotFoundException("There is no such recipient's wallet");
        }

    }

    private TransactionEntity createTransaction(Long senderWalletId, Long recipientWalletId, Double amount) {
        TransactionEntity transaction = TransactionEntity.builder()
                .senderWalletId(senderWalletId)
                .recipientWalletId(recipientWalletId)
                .amount(amount)
                .build();
        return transaction;
    }

    public List<TransactionDto> getTransactionHistory(Long id) {
        return transactionRepository.findBySenderWalletIdOrRecipientWalletId(id, id).stream()
                .map(transactionDtoConverter::createTransactionDto)
                .toList();
    }
}
