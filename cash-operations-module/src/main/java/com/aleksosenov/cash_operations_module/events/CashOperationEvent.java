package com.aleksosenov.cash_operations_module.events;

import com.aleksosenov.cash_operations_module.model.CashOperation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CashOperationEvent extends ApplicationEvent {
    private final CashOperation cashOperation;
    public CashOperationEvent(Object source, CashOperation cashOperation) {
        super(source);
        this.cashOperation = cashOperation;
    }
}
