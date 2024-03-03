package com.pacosal.bank.application;

import com.pacosal.bank.domain.Account;
import com.pacosal.bank.application.command.InsertAccountCommand;
import com.pacosal.bank.application.command.UpdateAccountCommand;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
  List<Account> findAll();

  Optional<Account> findById(final Long id);

  Optional<Account> findByAccountId(final String accountId);

  Account update(final UpdateAccountCommand command);

  Account save(final InsertAccountCommand command);

  void delete(final Long id);
}
