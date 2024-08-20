package com.aleksosenov.cash_operations_module.controller;

import com.aleksosenov.cash_operations_module.model.CashOperation;
import com.aleksosenov.cash_operations_module.model.dto.CashOperationRequest;
import com.aleksosenov.cash_operations_module.service.CashOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash-operation")
@Validated
@SpringBootApplication
public class CashOperationsController {

    @Autowired
    private CashOperationsService cashOperationsService;

    @PostMapping("/transaction")
    public String handleTransaction(@RequestBody CashOperationRequest cashOperationRequest) {
        return cashOperationsService.performCashOperation(CashOperation.fromRequest(cashOperationRequest));
    }


}