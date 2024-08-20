package com.aleksosenov.cash_operations_module.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Map;

@Getter
@Builder
@Component
public class Cashier {
    private final String username = "MARTINA";
    private final Map<Currency, Map<Denomination, Integer>> denominations;
}
