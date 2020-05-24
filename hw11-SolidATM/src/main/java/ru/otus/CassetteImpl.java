package ru.otus;

import java.util.Objects;

public class CassetteImpl implements Cassette {

    private final Currency value;
    private final int banknotesNumber;

    CassetteImpl(Currency value, int banknotesNumber) {
        this.value = value;
        this.banknotesNumber = banknotesNumber;
    }

    @Override
    public Currency getValue() {
        return value;
    }

    @Override
    public int getBanknotesNumber() {
        return banknotesNumber;
    }

    @Override
    public int compareTo(Cassette c) {
        return c.getValue().getNumericValue() - this.value.getNumericValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CassetteImpl cassette = (CassetteImpl) o;
        return value == cassette.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}