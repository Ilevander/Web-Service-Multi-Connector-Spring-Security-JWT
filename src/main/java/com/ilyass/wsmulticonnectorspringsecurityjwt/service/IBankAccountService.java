package com.ilyass.wsmulticonnectorspringsecurityjwt.service;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.AddBankAccountRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.AddBankAccountResponse;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.BankAccountDto;

import java.util.List;

public interface IBankAccountService {

    AddBankAccountResponse saveBankAccount(AddBankAccountRequest dto);

    List<BankAccountDto> getAllBankAccounts();

    BankAccountDto getBankAccountByRib(String rib);
}
