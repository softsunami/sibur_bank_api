package ru.kislyakow.user.wallet.service.api.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kislyakow.user.wallet.service.api.store.entities.WalletEntity;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
}
