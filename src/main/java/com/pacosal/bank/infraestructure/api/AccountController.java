package com.pacosal.bank.infraestructure.api;

import com.pacosal.bank.infraestructure.api.payload.AccountRequest;
import com.pacosal.bank.infraestructure.api.payload.AccountResponse;
import com.pacosal.bank.infraestructure.api.payload.AccountsResponse;
import com.pacosal.bank.infraestructure.api.service.AccountUseCaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "accounts")
public class AccountController {

  @Autowired
  AccountUseCaseServiceImpl service;

  @PostMapping
  @ResponseStatus(CREATED)
  public AccountResponse persistAccount(@RequestBody final AccountRequest request) {
    log.info("Persist account by request : {}", request);
    return AccountApiConverter.toAccountResponse(service.create(AccountApiConverter.toCreateAccountCommand(request)));
  }

  @GetMapping
  @ResponseStatus(OK)
  public AccountsResponse retrieveAccounts() {
    log.info("Retrieve accounts");
    return AccountsResponse.builder()
      .accounts(service.retrieveAccounts().stream()
        .map(AccountApiConverter::toAccountResponse)
        .collect(Collectors.toList())
      ).build();
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public AccountResponse retrieveAccountById(@PathVariable final Long id) {
    log.info("Retrieve account by id : {}", id);
    return AccountApiConverter.toAccountResponse(service.retrieveAccountById(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable final Long id) {
    log.info("Delete account by id : {}", id);
    service.delete(id);
  }

}
