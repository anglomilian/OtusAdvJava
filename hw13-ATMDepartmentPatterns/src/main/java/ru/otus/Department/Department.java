package ru.otus.Department;

import ru.otus.ATMs.*;

public interface Department {
    void addATM(ATM atm);
    void restore();
    int getCurrentBalance();
    void saveState(ATM atm);
}
