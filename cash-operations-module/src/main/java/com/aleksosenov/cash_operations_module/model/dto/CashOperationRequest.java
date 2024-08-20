package com.aleksosenov.cash_operations_module.model.dto;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import com.aleksosenov.cash_operations_module.model.OperationType;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public record CashOperationRequest(@NotNull(message = "Operation type is required") OperationType operationType,
                                   @NotNull(message = "Currency is required") Currency currency,
                                   @NotNull(message = "Denominations are required") Map<Denomination, Integer> denominationAmounts) {
}
