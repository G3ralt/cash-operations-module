package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.model.CashOperation;
import com.aleksosenov.cash_operations_module.model.Cashier;
import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiFunction;

@Service
public class CashOperationsService {

    @Autowired
    Cashier cashier;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public String performCashOperation(CashOperation cashOperation) {
        switch (cashOperation.getOperationType()) {
            case DEPOSIT -> executeOperation(cashOperation, Integer::sum);
            case WITHDRAW -> executeOperation(cashOperation, this::subtract);
        }
        return "OK";
    }

    private void executeOperation(CashOperation cashOperation, BiFunction<Integer, Integer, Integer> functionToApply) {
        Currency currency = cashOperation.getCurrency();
        Map<Denomination, Integer> operationAmounts = cashOperation.getDenominationAmounts();
        Map<Denomination, Integer> cashierCurrencyMap = cashier.getDenominations().get(currency);
        operationAmounts.forEach((denomination, amount) -> cashierCurrencyMap.merge(denomination,amount,functionToApply));
    }

    private int subtract(Integer a, Integer b) {
        return a - b;
    }



}
