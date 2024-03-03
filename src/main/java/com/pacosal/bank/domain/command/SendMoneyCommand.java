package com.pacosal.bank.domain.command;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Value
@Builder
public class SendMoneyCommand {
  @NotNull
  private String senderAccountId;
  @NotNull
  private String receiverAccountId;
  @NotNull
  private BigInteger amount;
}
