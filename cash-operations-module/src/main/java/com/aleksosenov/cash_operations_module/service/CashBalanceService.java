package com.aleksosenov.cash_operations_module.service;

import com.aleksosenov.cash_operations_module.events.CashBalanceEvent;
import com.aleksosenov.cash_operations_module.model.Cashier;
import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class CashBalanceService {

    @Autowired
    private Cashier cashier;
    @Autowired
    private CashBalanceFileService cashBalanceFileService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Map<Currency, Map<Denomination, Integer>> getCurrentBalance() {
        eventPublisher.publishEvent(new CashBalanceEvent(this, cashier.getDenominations()));
        return cashier.getDenominations();
    }
}
