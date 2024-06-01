package ru.kislyakow.transfer.processing.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class UserWalletServiceException extends RuntimeException {
    public UserWalletServiceException(String message) {
        super(message);
    }
}
