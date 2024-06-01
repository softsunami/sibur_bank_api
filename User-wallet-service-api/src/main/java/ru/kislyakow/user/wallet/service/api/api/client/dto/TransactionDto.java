package ru.kislyakow.user.wallet.service.api.api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;

public class TransactionDto {
    @JsonProperty("sender_wallet_id")
    private Long senderWalletId;

    @JsonProperty("recipient_wallet_id")
    private Long recipientWalletId;

    private Double amount;

    @Builder.Default()
    @JsonProperty("created_at")
    private Instant createdAt = null;

    @Builder.Default()
    @JsonProperty("successful_state")
    private Boolean successfulState = false;
}