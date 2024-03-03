package com.pacosal.bank.infraestructure.api.payload;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Value
@Builder
public class MoneyTransferRequest {
  @NotNull
  private String senderAccountId;
  @NotNull
  private String receiverAccountId;
  @NotNull
  private BigInteger amount;
}
