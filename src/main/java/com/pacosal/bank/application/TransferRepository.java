package com.pacosal.bank.application;

import com.pacosal.bank.domain.Transfer;
import com.pacosal.bank.application.command.InsertTransferCommand;

public interface TransferRepository {
  Transfer save(final InsertTransferCommand command);
}
