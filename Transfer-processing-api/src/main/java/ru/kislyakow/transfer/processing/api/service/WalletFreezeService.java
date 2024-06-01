package ru.kislyakow.transfer.processing.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class WalletFreezeService {

    private final Set<Long> frozenWallets = new HashSet<>();

    public Boolean isWalletFrozen(Long walletId) {
        return frozenWallets.contains(walletId);
    }

    public void frozeWallet(Long walletId) {
        frozenWallets.add(walletId);
    }

    public void unfrozeWallet(Long walletId) {
        frozenWallets.remove(walletId);
    }
}
