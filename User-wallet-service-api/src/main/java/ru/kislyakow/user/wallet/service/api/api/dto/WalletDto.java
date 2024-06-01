package ru.kislyakow.user.wallet.service.api.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WalletDto {
    @NotNull
    private Long id;

    @NotNull
    private Double balance;
}
