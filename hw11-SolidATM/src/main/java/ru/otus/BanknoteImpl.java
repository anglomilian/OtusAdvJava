package ru.otus;

import java.util.Objects;

public class BanknoteImpl implements Banknote {

    private final Nominal value;
    private final int banknotesNumber;

    BanknoteImpl(Nominal value, int banknotesNumber) {
        this.value = value;
        this.banknotesNumber = banknotesNumber;
    }

    @Override
    public Nominal getValue() {
        return value;
    }

    @Override
    public int getBanknotesNumber() {
        return banknotesNumber;
    }

    @Override
    public int compareTo(Banknote c) {
        return c.getValue().getNumericValue() - this.value.getNumericValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanknoteImpl banknote = (BanknoteImpl) o;
        return value == banknote.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}