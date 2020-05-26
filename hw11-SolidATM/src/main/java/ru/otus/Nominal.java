package ru.otus;

public enum Nominal {

    ONE_HUNDRED(100),
    FIVE_HUNDREDS(500),
    ONE_THOUSAND(1000),
    TWO_THOUSANDS(2000),
    FIVE_THOUSANDS(5000);

    private final int numValue;

    Nominal(int numValue) {
        this.numValue = numValue;
    }

    public int getNumericValue() {
        return numValue;
    }
}