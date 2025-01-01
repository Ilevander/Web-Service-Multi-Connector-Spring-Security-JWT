package com.ilyass.wsmulticonnectorspringsecurityjwt.service.model;

import jakarta.persistence.*;

import java.util.Date;

public class BankAccountTransaction {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Double amount;
    @ManyToOne
    private BankAccount bankAccount;

    @ManyToOne
    private User user;
}
