package com.pacosal.bank.application;

import com.pacosal.bank.domain.Account;
import com.pacosal.bank.domain.AccountUseCase;
import com.pacosal.bank.application.error.ErrorCode;
import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.domain.TransferMoneyUseCase;
import com.pacosal.bank.domain.command.CreateAccountCommand;
import com.pacosal.bank.domain.command.SendMoneyCommand;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

@Slf4j
public class AccountUseCaseImpl implements AccountUseCase {

  private final TransferMoneyUseCase transferMoneyUseCase;
  private final AccountRepository accountRepository;

  public AccountUseCaseImpl(TransferMoneyUseCase transferMoneyUseCase, AccountRepository accountRepository) {
    this.transferMoneyUseCase = transferMoneyUseCase;
    this.accountRepository = accountRepository;
  }

  @Override
  public Account retrieveAccountById(final Long id) {
    log.info("Retrieve account by id : {}", id);
    return accountRepository.findById(id)
      .orElseThrow(() -> ErrorCode.ACCOUNT_NOT_FOUND.asErrorResult(id));
  }

  @Override
  public List<Account> retrieveAccounts() {
    log.info("Retrieve all accounts ");
    return accountRepository.findAll();
  }

  @Override
  public Account create(final @Valid CreateAccountCommand command) {
    log.info("Create account by command : {}", command);
    return accountRepository.save(AccountServiceConverter.toInsertCommand(command));
  }

  @Override
  public void delete(final Long id) {
    log.info("Delete account by id : {}", id);
    accountRepository.delete(id);
  }

  @Override
  public Transfer transferMoney(final @Valid SendMoneyCommand command) {
    log.info("Transfer money with command : {}", command);
    return transferMoneyUseCase.transferMoney(command);
  }
}
