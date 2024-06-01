package ru.kislyakow.transfer.processing.api.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kislyakow.transfer.processing.api.exceptions.UserWalletServiceException;

import java.util.Optional;

@Component
public class UserWalletsClient {

    private static final String WALLET_CLIENT_ROUTE = "http://localhost:8080/api/";
    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<Double> getWalletBalance(Long walletId) {
        String userWalletsUrl = WALLET_CLIENT_ROUTE + "wallets/%d/balance".formatted(walletId);
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity(userWalletsUrl, Double.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    public void updateWalletsBalance(Long senderWalletId, Long recipientWalletId, Double amount) {
        String userWalletsUrl = UriComponentsBuilder.fromUriString(WALLET_CLIENT_ROUTE + "wallets/transfer")
                .queryParam("sender_wallet_id", senderWalletId)
                .queryParam("recipient_wallet_id", recipientWalletId)
                .queryParam("amount", amount)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            restTemplate.exchange(userWalletsUrl, HttpMethod.POST, new HttpEntity<>(headers), Void.class);
        } catch (HttpServerErrorException exception) {
            throw new UserWalletServiceException("Transaction failed.\n " + exception.getMessage());
        }
    }

}
