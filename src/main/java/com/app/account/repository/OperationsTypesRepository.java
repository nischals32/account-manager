package com.app.account.repository;

import com.app.account.models.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsTypesRepository extends JpaRepository<OperationType, Long> { }


