package ru.otus.ATMs;

import ru.otus.Department.Memento;

public interface ATM extends Listener{
    void addToDispenser(Nominal banknote, Integer banknotesNumber);
    void dispense(int amount);
    Integer getBalance();
    ATM clone();
    Memento saveState();
}