package com.app.account.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {
    public Account() {
    }

    public Account(long id, String documentNumber, double balance) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    @Column(name="Account_ID")
    private long id;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Column(name="document_number")
    private String documentNumber;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(name="balance")
    private double balance;
}
