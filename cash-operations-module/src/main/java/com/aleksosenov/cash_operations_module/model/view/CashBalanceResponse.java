package com.aleksosenov.cash_operations_module.model.view;

import com.aleksosenov.cash_operations_module.model.Currency;
import com.aleksosenov.cash_operations_module.model.Denomination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Setter
@Getter
public class CashBalanceResponse {
    private final Currency currency;
    private final int totalAmount;
    private final Map<Denomination, Integer> denominationAmounts;
}
