package com.aleksosenov.cash_operations_module.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Builder
@Component
@ToString
public class Cashier {
    private final String username = "MARTINA";
    @Setter
    private Map<Currency, Map<Denomination, Integer>> initialDenominations;
    @Setter
    private Map<Currency, Map<Denomination, Integer>> denominations;

    public static Integer getTotalForCurrency(Map.Entry<Currency, Map<Denomination, Integer>> currencyMapEntry) {
        return currencyMapEntry.getValue().entrySet().stream()
                .reduce(0, (subtotal, denominationEntry) -> Integer.sum(subtotal, denominationEntry.getKey().getValue() * denominationEntry.getValue()), Integer::sum);
    }
}
