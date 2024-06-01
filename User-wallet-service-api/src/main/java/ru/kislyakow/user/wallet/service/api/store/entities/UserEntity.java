package ru.kislyakow.user.wallet.service.api.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_user")
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String fullName;

    @Email()
    @Column(unique = true)
    private String email;

    @OneToOne
    private WalletEntity wallet;

    @Builder.Default
    private Date createdAt = new Date(System.currentTimeMillis());
}
