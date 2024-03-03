package com.pacosal.bank.domain;

import com.pacosal.bank.domain.command.SendMoneyCommand;

import javax.validation.Valid;

public interface TransferMoneyUseCase {
  Transfer transferMoney(@Valid final SendMoneyCommand command);
}
