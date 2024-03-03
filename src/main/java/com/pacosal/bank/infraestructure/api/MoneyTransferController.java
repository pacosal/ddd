package com.pacosal.bank.infraestructure.api;

import com.pacosal.bank.infraestructure.api.payload.MoneyTransferRequest;
import com.pacosal.bank.infraestructure.api.payload.MoneyTransferResponse;
import com.pacosal.bank.infraestructure.api.service.TransferMoneyUseCaseServiceImpl;
import com.pacosal.bank.application.TransferRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "accounts/transfer")
public class MoneyTransferController {

  @Autowired
  TransferMoneyUseCaseServiceImpl service;

  @Autowired
  TransferRepository transferRepository;
  
  @PostMapping()
  @ResponseStatus(CREATED)
  public MoneyTransferResponse transferMoney(@RequestBody final MoneyTransferRequest request) {
    log.info("Transfer money with request : {}", request);
    return AccountApiConverter.toMoneyTransferResponse(service.transferMoney(AccountApiConverter.toCommand(request)));
  }

}
