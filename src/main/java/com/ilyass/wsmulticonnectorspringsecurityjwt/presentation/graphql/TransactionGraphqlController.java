package com.ilyass.wsmulticonnectorspringsecurityjwt.presentation.graphql;

import com.ilyass.wsmulticonnectorspringsecurityjwt.common.CommonTools;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferResponse;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.GetTransactionListRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.TransactionDto;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class TransactionGraphqlController {
    private ITransactionService transactionService;
    private CommonTools commonTools;

    @MutationMapping
    public AddWirerTransferResponse addWirerTransfer(@Argument("dto") AddWirerTransferRequest dto) {
        return transactionService.wiredTransfer(dto);
    }

    @QueryMapping
    public List<TransactionDto> getTransactions(@Argument GetTransactionListRequest dto) {
        return transactionService.getTransactions(dto);
    }
}
