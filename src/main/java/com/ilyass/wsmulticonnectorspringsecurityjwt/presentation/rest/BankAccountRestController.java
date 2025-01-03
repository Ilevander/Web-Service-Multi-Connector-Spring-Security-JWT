package com.ilyass.wsmulticonnectorspringsecurityjwt.presentation.rest;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.AddBankAccountRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.AddBankAccountResponse;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.bankaccount.BankAccountDto;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.IBankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/bank")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasAnyRole('ADMIN','AGENT_GUICHET','CLIENT')")
public class BankAccountRestController {
    private final IBankAccountService bankAccountService;

    public BankAccountRestController(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('GET_ALL_BANK_ACCOUNT')")
    List<BankAccountDto> bankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('GET_BANK_ACCOUNT_BY_RIB')")
    BankAccountDto bankAccountByRib(@RequestParam(value = "rib") String rib) {
        return bankAccountService.getBankAccountByRib(rib);
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_BANK_ACCOUNT')")
    public ResponseEntity<AddBankAccountResponse> addBankAccount(@Valid @RequestBody AddBankAccountRequest dto) {
        return new ResponseEntity<>(bankAccountService.saveBankAccount(dto), HttpStatus.CREATED);
    }
}
