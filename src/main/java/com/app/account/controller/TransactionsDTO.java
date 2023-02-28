package com.app.account.controller;

public class TransactionsDTO {
    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    private long account_id;

    public long getOperation_type_id() {
        return operation_type_id;
    }

    public void setOperation_type_id(long operation_type_id) {
        this.operation_type_id = operation_type_id;
    }

    private long operation_type_id;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    private double amount;
}
