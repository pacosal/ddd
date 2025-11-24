package com.pacosal.bank.infraestructure.api.service;

import com.pacosal.bank.application.AccountRepository;
import com.pacosal.bank.application.AccountUseCaseImpl;
import com.pacosal.bank.application.TransferMoneyUseCaseImpl;
import com.pacosal.bank.application.TransferRepository;
import com.pacosal.bank.domain.Account;
import com.pacosal.bank.infraestructure.repository.AccountRepositoryImpl;
import com.pacosal.bank.infraestructure.repository.TransferRepositoryImpl;
import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.domain.command.CreateAccountCommand;
import com.pacosal.bank.domain.command.SendMoneyCommand;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class AccountUseCaseServiceImpl {

  AccountRepository accountRepository;
  TransferRepository transferRepository;

  private AccountUseCaseImpl accountUseCaseImpl = null;
  private TransferMoneyUseCaseImpl transferMoneyUseCaseImpl = null;

  
  public AccountUseCaseServiceImpl(AccountRepository accountRepository, TransferRepository transferRepository) {
    this.accountRepository = accountRepository;
    this.transferRepository = transferRepository;

    this.transferMoneyUseCaseImpl = new TransferMoneyUseCaseImpl(transferRepository, accountRepository);
    this.accountUseCaseImpl = new AccountUseCaseImpl(transferMoneyUseCaseImpl, accountRepository);
  }
  
  public Account retrieveAccountById(final Long id) {
    log.info("Retrieve account by id : {}", id);
    return accountUseCaseImpl.retrieveAccountById(id);
  }

  public List<Account> retrieveAccounts() {
    log.info("Retrieve all accounts ");
    return accountUseCaseImpl.retrieveAccounts();
  }

  public Account create(final @Valid CreateAccountCommand command) {
    log.info("Create account by command : {}", command);
    return accountUseCaseImpl.create(command);
  }

  public void delete(final Long id) {
    log.info("Delete account by id : {}", id);
    accountUseCaseImpl.delete(id);
  }

  public Transfer transferMoney(final @Valid SendMoneyCommand command) {
    log.info("Transfer money with command : {}", command);
    return accountUseCaseImpl.transferMoney(command);
  }
}
