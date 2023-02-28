package com.app.account.service;

import com.app.account.models.Account;
import com.app.account.models.OperationType;
import com.app.account.models.Transactions;
import com.app.account.repository.TransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@DisplayName("Verify transaction creation computes correct balance")
public class TransactionsServiceUnitTest {
    private TransactionsService service;
    @Mock
    TransactionsRepository txRepository;
    @BeforeEach
    void setup() {
       service = new TransactionsService(txRepository);
    }
    @Test
    @DisplayName("Withdrawal transaction test")
    public void testWithdrawal() {
        double startingBalance = 100;
        double txAmount = 30;
        Account acct = new Account(1, "document_1", startingBalance);
        OperationType operType = new OperationType("WITHDRAWAL");
        Transactions tx = new Transactions(acct, operType, txAmount);
        lenient().when(txRepository.save(tx))
                .thenReturn(tx);

        service.create(tx);
        assertEquals(tx.getAccount().getBalance(), startingBalance - txAmount);
    }
    @Test
    @DisplayName("Payment transaction test")
    public void testPayment() {
        double startingBalance = 100;
        double txAmount = 30;
        Account acct = new Account(1, "document_1", startingBalance);
        OperationType operType = new OperationType("PAYMENT");
        Transactions tx = new Transactions(acct, operType, txAmount);
        lenient().when(txRepository.save(tx))
                .thenReturn(tx);

        service.create(tx);
        assertEquals(tx.getAccount().getBalance(), startingBalance + txAmount);
    }
}
