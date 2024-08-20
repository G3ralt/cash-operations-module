package com.aleksosenov.cash_operations_module.config;

import com.aleksosenov.cash_operations_module.model.Cashier;
import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.service.BalanceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    Cashier cashier;
    @Autowired
    BalanceFileService balanceFileService;

    @Override
    public void run(String... args) throws Exception {
        Map<Currency, Map<Denomination, Integer>> currentBalance = balanceFileService.getBalanceFromFile();
        cashier.setDenominations(currentBalance);
        System.out.println(cashier);
    }
}
