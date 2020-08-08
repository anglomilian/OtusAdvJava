package ru.otus.Department;

import ru.otus.ATMs.*;

import java.util.*;

public class DispenserBuilderImpl implements DispenserBuilder{
    private Map<Nominal, Integer> dispenser = new EnumMap<>(Nominal.class);

    @Override
    public DispenserBuilder addBanknotes(Nominal n, Integer banknotesNumber) {
        dispenser.put(n, banknotesNumber);
        return this;
    }

    @Override
    public ATM getNewATM() {
        return new ATMImpl(dispenser);
    }
}
