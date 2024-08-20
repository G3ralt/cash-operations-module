package com.aleksosenov.cash_operations_module.model;

import lombok.Getter;

import java.util.Arrays;

public enum Denomination {
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    @Getter
    private final int value;

    Denomination(int i) {
        this.value = i;
    }

    public static Denomination findByValue(int value) {
        return Arrays.stream(Denomination.values()).filter(denomination -> denomination.value == value).findFirst().get();
    }
}
