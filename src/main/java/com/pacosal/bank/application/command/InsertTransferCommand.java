package com.pacosal.bank.application.command;

import com.pacosal.bank.domain.TransferResult;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigInteger;
import java.time.Instant;

@Value
@Builder
@With
public class InsertTransferCommand {
  private String senderAccountId;
  private String receiverAccountId;
  private BigInteger amount;
  private TransferResult result;
  private String detail;
  private Instant createdAt;
}
