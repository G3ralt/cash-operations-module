package com.aleksosenov.cash_operations_module.controller;

import com.aleksosenov.cash_operations_module.model.Cashier;
import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.model.view.CashBalanceResponse;
import com.aleksosenov.cash_operations_module.service.CashBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cash-balance")
public class CashBalanceController {

    @Autowired
    CashBalanceService cashBalanceService;

    @GetMapping
    public ResponseEntity<List<CashBalanceResponse>> getBalance() {
        Map<Currency, Map<Denomination, Integer>> currentBalance = cashBalanceService.getCurrentBalance();
        List<CashBalanceResponse> body = currentBalance.entrySet().stream()
                .map(currencyMapEntry -> CashBalanceResponse.builder()
                        .currency(currencyMapEntry.getKey())
                        .totalAmount(Cashier.getTotalForCurrency(currencyMapEntry))
                        .denominationAmounts(currencyMapEntry.getValue())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(body);
    }
}
