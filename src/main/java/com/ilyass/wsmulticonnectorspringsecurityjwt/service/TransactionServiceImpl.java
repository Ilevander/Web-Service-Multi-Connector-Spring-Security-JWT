package com.ilyass.wsmulticonnectorspringsecurityjwt.service;

import com.ilyass.wsmulticonnectorspringsecurityjwt.dao.BankAccountRepository;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dao.BankAccountTransactionRepository;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dao.UserRepository;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.AddWirerTransferResponse;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.GetTransactionListRequest;
import com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.transaction.TransactionDto;
import com.ilyass.wsmulticonnectorspringsecurityjwt.enums.AccountStatus;
import com.ilyass.wsmulticonnectorspringsecurityjwt.enums.TransactionType;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.exception.BusinessException;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.BankAccount;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.BankAccountTransaction;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.GetTransactionListBo;
import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountTransactionRepository bankAccountTransactionRepository;

    private final UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public AddWirerTransferResponse wiredTransfer(AddWirerTransferRequest dto) {

        BankAccountTransaction transactionFrom = BankAccountTransaction.builder().
                amount(dto.getAmount()).
                transactionType(TransactionType.DEBIT).
                bankAccount(BankAccount.builder().rib(dto.getRibFrom()).build()).
                user(new User(dto.getUsername())).
                build();

        BankAccountTransaction transactionTo = BankAccountTransaction.builder().
                amount(dto.getAmount()).
                transactionType(TransactionType.CREDIT).
                bankAccount(BankAccount.builder().rib(dto.getRibTo()).build()).
                user(new User(dto.getUsername())).
                build();

        String username = transactionFrom.getUser().getUsername();
        String ribFrom = transactionFrom.getBankAccount().getRib();
        String ribTo = transactionTo.getBankAccount().getRib();
        Double amount = transactionFrom.getAmount();

        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new BusinessException(String.format("User [%s] doesn't exist", username)));

        BankAccount bankAccountFrom = bankAccountRepository.findByRib(ribFrom).
                orElseThrow(() -> new BusinessException(String.format("No bank account have the rib %s", ribFrom)));

        BankAccount bankAccountTo = bankAccountRepository.findByRib(ribTo).
                orElseThrow(() -> new BusinessException(String.format("No bank account have the rib %s", ribTo)));

        checkBusinessRules(bankAccountFrom, bankAccountTo, amount);
        //On débite le compte demandeur
        bankAccountFrom.setAmount(bankAccountFrom.getAmount() - amount);
        //On crédite le compte destinataire
        bankAccountTo.setAmount(bankAccountTo.getAmount() + amount);

        transactionFrom.setCreatedAt(new Date());
        transactionFrom.setUser(user);
        transactionFrom.setBankAccount(bankAccountFrom);

        transactionTo.setCreatedAt(new Date());
        transactionTo.setUser(user);
        transactionTo.setBankAccount(bankAccountTo);
        bankAccountTransactionRepository.save(transactionFrom);
        bankAccountTransactionRepository.save(transactionTo);
        return AddWirerTransferResponse.builder().
                message(String.format("the transfer of an amount of %s from the %s bank account to %s was carried out successfully",
                        dto.getAmount(), dto.getRibFrom(), dto.getRibTo())).
                transactionFrom(modelMapper.map(transactionFrom, TransactionDto.class)).
                transactionTo(modelMapper.map(transactionTo, TransactionDto.class)).
                build();
    }

    private void checkBusinessRules(BankAccount bankAccountFrom, BankAccount bankAccountTo, Double amount) {

        if (bankAccountFrom.getAccountStatus().equals(AccountStatus.CLOSED))
            throw new BusinessException(String.format("the bank account %s is closed !!", bankAccountFrom.getRib()));

        if (bankAccountFrom.getAccountStatus().equals(AccountStatus.BLOCKED))
            throw new BusinessException(String.format("the bank account %s is blocked !!", bankAccountFrom.getRib()));

        if (bankAccountTo.getAccountStatus().equals(AccountStatus.CLOSED))
            throw new BusinessException(String.format("the bank account %s is closed !!", bankAccountTo.getRib()));

        if (bankAccountTo.getAccountStatus().equals(AccountStatus.BLOCKED))
            throw new BusinessException(String.format("the bank account %s is blocked !!", bankAccountTo.getRib()));

        if (bankAccountFrom.getAmount() < amount)
            throw new BusinessException(String.format("the balance of account number %s is less than %s", bankAccountFrom.getRib(), amount));
    }


    @Override
    public List<TransactionDto> getTransactions(GetTransactionListRequest requestDTO) {
        GetTransactionListBo data = modelMapper.map(requestDTO, GetTransactionListBo.class);
        return bankAccountTransactionRepository.findByBankAccount_RibAndCreatedAtBetween(
                        data.getRib(), data.getDateFrom(), data.getDateTo()).
                stream().map(bo -> modelMapper.map(bo, TransactionDto.class)).collect(Collectors.toList());
    }
}
