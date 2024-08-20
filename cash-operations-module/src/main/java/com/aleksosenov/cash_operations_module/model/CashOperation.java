package com.aleksosenov.cash_operations_module.model;

import lombok.Data;

@Data
public class CashOperation {
    private final TransactionType transactionType;
    private final Currency currency;
    private final Denomination denomination;
    private final int amount;
}
