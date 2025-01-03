package com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.BankAccountDto;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransactionDto {
    private Long id;
    private String createdAt;
    private String transactionType;
    private Double amount;
    private BankAccountDto bankAccount;
    private UserDto user;
}
