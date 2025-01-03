package com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.customer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ilyass.wsmulticonnectorspringsecurityjwt.enums.AccountStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddBankAccountResponse {
    private String message;
    private Long id;
    private String rib;
    private Double amount;
    private String createdAt;
    private AccountStatus accountStatus;
    private CustomerDto customer;
}
