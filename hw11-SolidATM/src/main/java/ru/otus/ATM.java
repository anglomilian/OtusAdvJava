package ru.otus;

import java.util.Set;

public interface ATM {
    void addToDispenser(Cassette cassette);
    Set<Cassette> dispense(int amount);
    int getBalance();
}