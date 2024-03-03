package com.pacosal.bank.domain;

import com.pacosal.bank.domain.command.CreateAccountCommand;
import com.pacosal.bank.domain.command.SendMoneyCommand;

import javax.validation.Valid;
import java.util.List;

public interface AccountUseCase {
  Account retrieveAccountById(final Long id);

  List<Account> retrieveAccounts();

  Account create(final @Valid CreateAccountCommand command);

  void delete(final Long id);

  Transfer transferMoney(@Valid final SendMoneyCommand command);
}
