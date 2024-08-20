package com.aleksosenov.cash_operations_module.config;

import com.aleksosenov.cash_operations_module.model.Cashier;
import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.service.CashBalanceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    Cashier cashier;
    @Autowired
    CashBalanceFileService cashBalanceFileService;

    @Override
    public void run(String... args) throws Exception {
        Map<Currency, Map<Denomination, Integer>> initialBalance = cashBalanceFileService.getBalanceFromFile();
        cashier.setDenominations(initialBalance);
        cashier.setInitialDenominations(initialBalance);
    }
}
