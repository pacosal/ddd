package com.pacosal.bank.infraestructure.api.service;

import com.pacosal.bank.infraestructure.repository.AccountRepositoryImpl;
import com.pacosal.bank.infraestructure.repository.TransferRepositoryImpl;
import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.domain.TransferMoneyUseCase;
import com.pacosal.bank.application.TransferMoneyUseCaseImpl;
import com.pacosal.bank.domain.command.SendMoneyCommand;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class TransferMoneyUseCaseServiceImpl implements TransferMoneyUseCase {

  @Autowired
  AccountRepositoryImpl accountRepository;

  @Autowired
  TransferRepositoryImpl transferRepository;

  private TransferMoneyUseCase transferMoneyUseCase = null;

  // Crea el objeto transferMoneyUseCase para ser utilizado desde Springboot
  public void init() {
    if (transferMoneyUseCase == null) {
      transferMoneyUseCase = new TransferMoneyUseCaseImpl(transferRepository, accountRepository);
    }
  }

  @Override
  public Transfer transferMoney(@Valid SendMoneyCommand command) {
    log.info("Transfer money with command : {}", command);
    init();
    return transferMoneyUseCase.transferMoney(command);
  }
}
