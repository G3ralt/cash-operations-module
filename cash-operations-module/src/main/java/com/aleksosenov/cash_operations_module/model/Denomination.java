package com.aleksosenov.cash_operations_module.model;

import java.util.Arrays;
import java.util.EnumMap;

public enum Denomination {
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private final int value;

    Denomination(int i) {
        this.value = i;
    }

    public static Denomination findByValue(int value) {
        return Arrays.stream(Denomination.values()).filter(denomination -> denomination.value == value).findFirst().get();
    }
}
