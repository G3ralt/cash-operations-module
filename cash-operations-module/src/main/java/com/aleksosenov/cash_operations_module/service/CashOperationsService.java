package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.model.CashOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CashOperationsService {


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public String performTransaction(CashOperation cashOperation) {
        return null;
    }

}
