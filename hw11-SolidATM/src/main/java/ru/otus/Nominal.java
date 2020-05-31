package ru.otus;

public enum Nominal {
    FIVE_THOUSANDS(5000),
    TWO_THOUSANDS(2000),
    ONE_THOUSAND(1000),
    FIVE_HUNDREDS(500),
    ONE_HUNDRED(100);

    private final int numValue;

    Nominal(int numValue) {
        this.numValue = numValue;
    }

    public int getNumericValue() {
        return numValue;
    }
}