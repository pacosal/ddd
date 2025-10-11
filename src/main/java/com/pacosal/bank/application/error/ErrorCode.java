package com.pacosal.bank.application.error;

import java.text.MessageFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    SENDER_ACCOUNT_NOT_FOUND("Sender Account is not exist id : {0}"),
    RECEIVER_ACCOUNT_NOT_FOUND("Receiver Account is not exist id : {0}"),
    ACCOUNT_NOT_FOUND("Account is not exist id : {0}"),
    NOT_SUFFICIENT_BALANCE("Not sufficient balance in account id : {0}");

    private String message;

    public ErrorResultException asErrorResult(final Object... params) {
        return ErrorResultException.builder()
                .message(MessageFormat.format(message, params))
                .build();
    }
}
