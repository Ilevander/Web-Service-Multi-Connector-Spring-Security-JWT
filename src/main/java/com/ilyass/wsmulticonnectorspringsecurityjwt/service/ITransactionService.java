package com.ilyass.wsmulticonnectorspringsecurityjwt.service;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferResponse;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.GetTransactionListRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.TransactionDto;

import java.util.List;

public interface ITransactionService {
    AddWirerTransferResponse wiredTransfer(AddWirerTransferRequest dto);

    List<TransactionDto> getTransactions(GetTransactionListRequest dto);
}
