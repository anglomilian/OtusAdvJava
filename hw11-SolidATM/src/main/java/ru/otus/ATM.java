package ru.otus;

import java.util.Set;

public interface ATM {
    void addToDispenser(Banknote cassette);
    Set<Banknote> dispense(int amount);
    int getBalance();
}