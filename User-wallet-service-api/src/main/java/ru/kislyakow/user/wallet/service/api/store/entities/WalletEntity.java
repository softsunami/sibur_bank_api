package ru.kislyakow.user.wallet.service.api.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.sql.Date;
import java.time.Instant;

@Entity(name = "t_wallet")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @PositiveOrZero
    private Double balance;

    @Builder.Default
    Date createdAt = new Date(System.currentTimeMillis());
}
