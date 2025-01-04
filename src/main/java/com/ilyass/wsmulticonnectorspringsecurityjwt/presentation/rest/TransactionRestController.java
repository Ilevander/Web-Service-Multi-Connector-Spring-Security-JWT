package com.ilyass.wsmulticonnectorspringsecurityjwt.presentation.rest;

import com.ilyass.wsmulticonnectorspringsecurityjwt.common.CommonTools;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferResponse;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.GetTransactionListRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.TransactionDto;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.ITransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rest/transaction")
@PreAuthorize("hasRole('CLIENT')")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionRestController {
    private ITransactionService transactionService;
    private CommonTools commonTools;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADD_WIRED_TRANSFER')")
    public ResponseEntity<AddWirerTransferResponse> addWirerTransfer(@Valid @RequestBody AddWirerTransferRequest dto) {
        return new ResponseEntity<>(transactionService.wiredTransfer(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('GET_TRANSACTIONS')")
    public List<TransactionDto> getTransactions(GetTransactionListRequest dto) {
        return transactionService.getTransactions(dto);
    }
}
