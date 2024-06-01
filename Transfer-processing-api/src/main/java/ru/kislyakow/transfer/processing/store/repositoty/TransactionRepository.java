package ru.kislyakow.transfer.processing.store.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kislyakow.transfer.processing.store.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findBySenderWalletIdOrRecipientWalletId(Long senderWalletId, Long recipientWalletId);
}
