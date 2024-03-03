package com.pacosal.bank.infraestructure.repository;

import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.application.TransferRepository;
import com.pacosal.bank.application.command.InsertTransferCommand;
import com.pacosal.bank.infraestructure.repository.jpa.TransferJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.pacosal.bank.infraestructure.repository.RepositoryConverter.toTransfer;
import static com.pacosal.bank.infraestructure.repository.RepositoryConverter.toTransferEntity;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TransferRepositoryImpl implements TransferRepository {

  private final TransferJpaRepository moneyTransferJpaRepository;

  @Override
  public Transfer save(final InsertTransferCommand command) {
    log.info("Save money transfer with command : {}", command);
    return toTransfer(moneyTransferJpaRepository.save(toTransferEntity(command)));
  }

}
