package ru.otus.Department;

import ru.otus.ATMs.*;

public class Memento {
    private final ATM atm;

    public Memento(ATM atm) {
        this.atm = atm;
    }

    public ATM getAtm() {
        return atm;
    }
}
