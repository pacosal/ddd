package com.pacosal.bank.infraestructure.api.payload;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Value
@Builder
public class AccountRequest {
  @NotNull
  private String accountId;
  @NotNull
  private String name;
  @NotNull
  private String owner;
  private BigInteger balance;

}
