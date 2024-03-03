package com.pacosal.bank.infraestructure.api.service;

import com.pacosal.bank.application.AccountUseCaseImpl;
import com.pacosal.bank.application.TransferMoneyUseCaseImpl;
import com.pacosal.bank.domain.Account;
import com.pacosal.bank.infraestructure.repository.AccountRepositoryImpl;
import com.pacosal.bank.infraestructure.repository.TransferRepositoryImpl;
import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.domain.TransferMoneyUseCase;
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

  @Autowired
  AccountRepositoryImpl accountRepository;

  @Autowired
  TransferRepositoryImpl transferRepository;

  private TransferMoneyUseCase transferMoneyUseCase = null;
  private AccountUseCaseImpl accountUseCaseImpl = null;

  // Crea el objeto accountUseCaseImpl para ser utilizado desde Springboot
  public void init() {
    if (accountUseCaseImpl == null) {
      transferMoneyUseCase = new TransferMoneyUseCaseImpl(transferRepository, accountRepository);
      accountUseCaseImpl = new AccountUseCaseImpl(transferMoneyUseCase, accountRepository);
    }
  }
  
  public Account retrieveAccountById(final Long id) {
    log.info("Retrieve account by id : {}", id);
    init();
    return accountUseCaseImpl.retrieveAccountById(id);
  }

  public List<Account> retrieveAccounts() {
    log.info("Retrieve all accounts ");
    init();
    return accountUseCaseImpl.retrieveAccounts();
  }

  public Account create(final @Valid CreateAccountCommand command) {
    log.info("Create account by command : {}", command);
    init();
    return accountUseCaseImpl.create(command);
  }

  public void delete(final Long id) {
    log.info("Delete account by id : {}", id);
    init();
    accountUseCaseImpl.delete(id);
  }

  public Transfer transferMoney(final @Valid SendMoneyCommand command) {
    log.info("Transfer money with command : {}", command);
    init();
    return accountUseCaseImpl.transferMoney(command);
  }
}
