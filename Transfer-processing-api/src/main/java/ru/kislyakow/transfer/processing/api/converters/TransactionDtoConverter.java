package ru.kislyakow.transfer.processing.api.converters;

import org.springframework.stereotype.Component;
import ru.kislyakow.transfer.processing.api.dto.TransactionDto;
import ru.kislyakow.transfer.processing.store.entity.TransactionEntity;

@Component
public class TransactionDtoConverter {

    public TransactionDto createTransactionDto(TransactionEntity transaction) {
        TransactionDto transactionDto = TransactionDto.builder()
                .senderWalletId(transaction.getSenderWalletId())
                .recipientWalletId(transaction.getRecipientWalletId())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .successfulState(transaction.getSuccessfulState())
                .build();

        return transactionDto;
    }

}
