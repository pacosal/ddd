package com.pacosal.bank.application;

import com.pacosal.bank.application.error.ErrorCode;
import com.pacosal.bank.application.error.ErrorResultException;
import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.domain.TransferMoneyUseCase;
import com.pacosal.bank.domain.TransferResult;
import com.pacosal.bank.application.command.InsertTransferCommand;
import com.pacosal.bank.application.command.UpdateAccountCommand;
import com.pacosal.bank.domain.command.SendMoneyCommand;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
public class TransferMoneyUseCaseImpl implements TransferMoneyUseCase {

  private final TransferRepository transferRepository;
  private final AccountRepository accountRepository;

  public TransferMoneyUseCaseImpl(TransferRepository transferRepository, AccountRepository accountRepository) {
    this.transferRepository = transferRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public Transfer transferMoney(@Valid SendMoneyCommand command) {
    log.info("Transfer money with command : {}", command);
    try {
      var sender = accountRepository.findByAccountId(command.getSenderAccountId())
        .orElseThrow(() -> ErrorCode.SENDER_ACCOUNT_NOT_FOUND.asErrorResult(command.getSenderAccountId()));
      var receiver = accountRepository.findByAccountId(command.getReceiverAccountId())
        .orElseThrow(() -> ErrorCode.RECEIVER_ACCOUNT_NOT_FOUND.asErrorResult(command.getSenderAccountId()));

      if (sender.getBalance().compareTo(command.getAmount()) < 0) {
        throw ErrorCode.NOT_SUFFICIENT_BALANCE.asErrorResult(command.getSenderAccountId());
      }

      sender.withdraw(command.getAmount());
      receiver.deposit(command.getAmount());

      accountRepository.update(UpdateAccountCommand.builder()
        .balance(sender.getBalance())
        .id(sender.getId())
        .name(sender.getName())
        .owner(sender.getOwner())
        .accountId(sender.getAccountId())
        .build());

      accountRepository.update(UpdateAccountCommand.builder()
        .balance(receiver.getBalance())
        .id(receiver.getId())
        .name(receiver.getName())
        .owner(receiver.getOwner())
        .accountId(receiver.getAccountId())
        .build());

      return transferRepository.save(InsertTransferCommand.builder()
        .senderAccountId(command.getSenderAccountId())
        .receiverAccountId(command.getReceiverAccountId())
        .amount(command.getAmount())
        .result(TransferResult.SUCCESSFUL)
        .build());

    } catch (final ErrorResultException ex) {
      log.error("Transaction failed : {}", ex.getMessage());
      transferRepository.save(InsertTransferCommand.builder()
        .senderAccountId(command.getSenderAccountId())
        .receiverAccountId(command.getReceiverAccountId())
        .amount(command.getAmount())
        .result(TransferResult.FAILED)
        .build());
      throw ex;
    }
  }
}
