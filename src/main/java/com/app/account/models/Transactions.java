package com.app.account.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Transactions {
    public long getId() {
        return id;
    }

    @Id()
    @Column(name="Transaction_id")
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName = "Account_ID")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @ManyToOne
    @JoinColumn(name="operation_type_id", referencedColumnName = "OperationType_ID")
    private OperationType operationType;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name="Amount")
    private double amount;

    @Column(name="EventDate")
    @CreatedDate
    private Date eventDate;

    public Transactions() {
    }

    public Transactions(Account account, OperationType operationType, double amount) {
        super();
        this.account = account;
        this.operationType = operationType;
        this.amount = amount;
    }
}
