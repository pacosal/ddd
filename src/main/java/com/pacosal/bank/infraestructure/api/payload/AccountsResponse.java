package com.pacosal.bank.infraestructure.api.payload;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AccountsResponse {
  List<AccountResponse> accounts;
}
