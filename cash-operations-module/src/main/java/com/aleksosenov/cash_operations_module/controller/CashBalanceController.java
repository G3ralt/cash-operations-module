package com.aleksosenov.cash_operations_module.controller;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.service.CashBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/cash-balance")
public class CashBalanceController {

    @Autowired
    CashBalanceService cashBalanceService;

    @GetMapping
    public ResponseEntity<Map<Currency, Map<Denomination, Integer>>> getBalance() {
        return ResponseEntity.ok(cashBalanceService.getCurrentBalance());
    }
}
