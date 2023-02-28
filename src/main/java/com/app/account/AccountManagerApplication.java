package com.app.account;

import com.app.account.models.OperationType;
import com.app.account.repository.OperationsTypesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class AccountManagerApplication {
	private final Logger logger = LoggerFactory.getLogger(AccountManagerApplication.class);
	@Autowired
	private OperationsTypesRepository operationsRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountManagerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		if(queryAllOperationTypes() == 0) {
			createOperationsType("CASH PURCHASE");
			createOperationsType("INSTALLMENT PURCHASE");
			createOperationsType("WITHDRAWAL");
			createOperationsType("PAYMENT");
			queryAllOperationTypes();
		}
	}

	private void createOperationsType(String description) {
		OperationType operation = new OperationType(description);
		logger.info("Saving new operation type..." + description);
		this.operationsRepository.save(operation);
	}

	private int queryAllOperationTypes() {
		List<OperationType> allOperations = this.operationsRepository.findAll();
		logger.info("Number of operation types: " + allOperations.size());
		return allOperations.size();
	}
}