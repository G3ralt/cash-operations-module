package com.aleksosenov.cash_operations_module.model;

import com.aleksosenov.cash_operations_module.model.dto.CashOperationRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CashOperation {
    private final OperationType operationType;
    private final Currency currency;
    private final Map<Denomination, Integer> denominationAmounts;

    public static CashOperation fromRequest(CashOperationRequest request) {
        return CashOperation.builder()
                .operationType(request.getOperationType())
                .currency(request.getCurrency())
                .denominationAmounts(request.getDenominationAmounts())
                .build();
    }
}
