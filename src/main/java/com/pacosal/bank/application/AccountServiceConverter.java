package com.pacosal.bank.application;

import com.pacosal.bank.application.command.InsertAccountCommand;
import com.pacosal.bank.domain.command.CreateAccountCommand;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.util.Optional;

@UtilityClass
public class AccountServiceConverter {
  public static InsertAccountCommand toInsertCommand(final CreateAccountCommand command) {
    return InsertAccountCommand.builder()
      .accountId(command.getAccountId())
      .name(command.getName())
      .owner(command.getOwner())
      .balance(Optional.ofNullable(command.getBalance())
        .orElse(BigInteger.ZERO))
      .build();
  }
}
