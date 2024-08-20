package com.aleksosenov.cash_operations_module.model.dto;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.model.OperationType;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public final class CashOperationRequest {
    private final @NotNull(message = "Operation type is required") OperationType operationType;
    private final @NotNull(message = "Currency is required") Currency currency;
    private final @NotNull(message = "Denominations are required") Map<Denomination, Integer> denominationAmounts;
}
