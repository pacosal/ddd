package com.pacosal.bank.application.error;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@Builder
public class ErrorResultException extends RuntimeException {
    private String message;
}
