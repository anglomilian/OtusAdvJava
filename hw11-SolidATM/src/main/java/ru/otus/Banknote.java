package ru.otus;

public interface Banknote extends Comparable<Banknote> {
    Nominal getValue();
    int getBanknotesNumber();
}