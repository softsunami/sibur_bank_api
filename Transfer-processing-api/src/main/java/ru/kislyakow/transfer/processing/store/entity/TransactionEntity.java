package ru.kislyakow.transfer.processing.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "t_transaction_hist")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private Long senderWalletId;

    @NotNull
    private Long recipientWalletId;

    @Positive
    private Double amount;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Builder.Default
    private Boolean successfulState = false;
}
