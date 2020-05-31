package ru.otus;

import java.util.Set;

public interface ATM {
    void addToDispenser(Nominal banknote, Integer banknotesNumber);
    void dispense(int amount);
    int getBalance();
}