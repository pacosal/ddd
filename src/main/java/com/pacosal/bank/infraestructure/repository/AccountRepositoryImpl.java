package com.pacosal.bank.infraestructure.repository;

import com.pacosal.bank.domain.Account;
import com.pacosal.bank.application.AccountRepository;
import com.pacosal.bank.application.command.InsertAccountCommand;
import com.pacosal.bank.application.command.UpdateAccountCommand;
import com.pacosal.bank.infraestructure.repository.jpa.AccountJpaRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pacosal.bank.infraestructure.repository.RepositoryConverter.toAccount;
import static com.pacosal.bank.infraestructure.repository.RepositoryConverter.toAccountEntity;

@Slf4j
@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Autowired
  AccountJpaRepository accountJpaRepository;

  @Override
  public List<Account> findAll() {
    log.info("Find all accounts ");
    return accountJpaRepository.findAll().stream()
      .map(RepositoryConverter::toAccount)
      .collect(Collectors.toList());
  }

  @Override
  public Optional<Account> findById(final Long id) {
    log.info("Find account by id : {}", id);
    return accountJpaRepository.findById(id)
      .map(RepositoryConverter::toAccount);
  }

  @Override
  public Optional<Account> findByAccountId(final String accountId) {
    log.info("Find account by accountID : {}", accountId);
    return accountJpaRepository.findByAccountId(accountId)
      .map(RepositoryConverter::toAccount);
  }

  @Override
  public Account update(final UpdateAccountCommand command) {
    log.info("Update account by command : {}", command);
    return toAccount(accountJpaRepository.save(toAccountEntity(command)));
  }

  @Override
  public Account save(final InsertAccountCommand command) {
    log.info("Save account by command : {}", command);
    return toAccount(accountJpaRepository.save(toAccountEntity(command)));
  }

  @Override
  public void delete(final Long id) {
    log.info("Delete account by id : {}", id);
    accountJpaRepository.deleteById(id);
  }
}
