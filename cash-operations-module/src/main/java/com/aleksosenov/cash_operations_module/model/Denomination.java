package com.aleksosenov.cash_operations_module.model;

public enum Denomination {
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private final int value;

    Denomination(int i) {
        this.value = i;
    }
}
