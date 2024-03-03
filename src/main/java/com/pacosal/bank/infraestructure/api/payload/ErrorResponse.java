package com.pacosal.bank.infraestructure.api.payload;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
  private String type;
  private String code;
  private int status;
  private String message;
}
