package com.aleksosenov.cash_operations_module.events;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

public class CashBalanceEvent extends ApplicationEvent {
    @Getter
    private final Map<Currency, Map<Denomination, Integer>> currentBalance;

    public CashBalanceEvent(Object source, Map<Currency, Map<Denomination, Integer>> currentBalance) {
        super(source);
        this.currentBalance = currentBalance;
    }
}
