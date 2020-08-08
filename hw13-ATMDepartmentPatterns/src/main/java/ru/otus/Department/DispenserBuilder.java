package ru.otus.Department;

import ru.otus.ATMs.*;

public interface DispenserBuilder {
    DispenserBuilder addBanknotes(Nominal n, Integer banknotesNumber);
    ATM getNewATM();
}