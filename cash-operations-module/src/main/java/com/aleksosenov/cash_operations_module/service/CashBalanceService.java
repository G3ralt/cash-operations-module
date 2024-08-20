package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CashBalanceService {

    @Autowired
    BalanceFileService balanceFileService;

    public Map<Currency, Map<Denomination, Integer>> getCurrentBalance() {
        return balanceFileService.getBalanceFromFile();
    }
}
