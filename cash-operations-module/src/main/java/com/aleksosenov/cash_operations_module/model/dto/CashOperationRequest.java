package com.aleksosenov.cash_operations_module.model.dto;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.model.OperationType;
import lombok.Data;

import java.util.Map;

@Data
public class CashOperationRequest {
    private final OperationType operationType;
    private final Currency currency;
    private final Map<Denomination, Integer> denominationAmounts;
}
