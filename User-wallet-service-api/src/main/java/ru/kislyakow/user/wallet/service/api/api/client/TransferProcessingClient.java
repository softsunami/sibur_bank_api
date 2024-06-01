package ru.kislyakow.user.wallet.service.api.api.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kislyakow.user.wallet.service.api.api.dto.TransactionDto;

import java.util.List;

@Component
public class TransferProcessingClient {

    private final static String PROCESSING_ROUTE = "http://localhost:8081/api/processing/";

    public List<TransactionDto> getTransactionHistory(Long walletId) {
        RestTemplate restTemplate = new RestTemplate();
        String transactionsUrl = PROCESSING_ROUTE + "transfer/" + walletId;

        ResponseEntity<List<TransactionDto>> responseEntity = restTemplate.exchange(
                transactionsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return responseEntity.getBody();
    }

}
