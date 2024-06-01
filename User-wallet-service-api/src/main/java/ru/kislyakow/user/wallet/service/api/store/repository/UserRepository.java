package ru.kislyakow.user.wallet.service.api.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kislyakow.user.wallet.service.api.store.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
