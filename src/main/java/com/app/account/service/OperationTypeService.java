package com.app.account.service;

import com.app.account.models.Account;
import com.app.account.models.OperationType;
import com.app.account.repository.OperationsTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationTypeService {
    @Autowired
    private OperationsTypesRepository repository;

    public OperationType create(OperationType operationType) {
        repository.save(operationType);
        return operationType;
    }

    public Optional<OperationType> read(long id) {
        return repository.findById(id);
    }

    public List<OperationType> readAll() {
        return repository.findAll();
    }
}
