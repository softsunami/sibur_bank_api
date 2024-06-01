package ru.kislyakow.transfer.processing.api.converters;

import org.junit.jupiter.api.Test;
import ru.kislyakow.transfer.processing.api.dto.TransactionDto;
import ru.kislyakow.transfer.processing.store.entity.TransactionEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDtoConverterTest {

    private final TransactionDtoConverter transactionDtoConverter = new TransactionDtoConverter();

    @Test
    public void testCorrectTransactionDtoConverter() {
        TransactionEntity transaction = TransactionEntity.builder()
                .id(UUID.randomUUID())
                .recipientWalletId(1L)
                .senderWalletId(2L)
                .amount(5000D)
                .successfulState(true)
                .build();

        TransactionDto transactionDto = transactionDtoConverter.createTransactionDto(transaction);

        assertEquals(transaction.getSenderWalletId(), transactionDto.getSenderWalletId());
        assertEquals(transaction.getRecipientWalletId(), transactionDto.getRecipientWalletId());
        assertEquals(transaction.getAmount(), transactionDto.getAmount());
        assertEquals(transaction.getCreatedAt(), transactionDto.getCreatedAt());
        assertEquals(transaction.getSuccessfulState(), transactionDto.getSuccessfulState());
    }

    @Test
    public void testEmptyTransactionDtoConverter() {
        TransactionEntity transaction = new TransactionEntity();
        TransactionDto transactionDto = transactionDtoConverter.createTransactionDto(transaction);

        assertEquals(transaction.getSenderWalletId(), transactionDto.getSenderWalletId());
        assertEquals(transaction.getRecipientWalletId(), transactionDto.getRecipientWalletId());
        assertEquals(transaction.getAmount(), transactionDto.getAmount());
        assertEquals(transaction.getCreatedAt(), transactionDto.getCreatedAt());
        assertEquals(transaction.getSuccessfulState(), transactionDto.getSuccessfulState());
    }

}