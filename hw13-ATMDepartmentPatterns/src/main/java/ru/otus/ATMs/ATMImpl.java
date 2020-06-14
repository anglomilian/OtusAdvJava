package ru.otus.ATMs;

import ru.otus.Department.Memento;

import java.util.*;

public class ATMImpl implements ATM, Cloneable {

    private Map<Nominal, Integer> dispenser;

    public ATMImpl(Map<Nominal, Integer> moneyForDispenser){
        dispenser = moneyForDispenser;
    }

    @Override
    public void addToDispenser(Nominal banknote, Integer banknotesNumber) {
        dispenser.put(banknote, banknotesNumber);
    }

    @Override
    public void dispense(int clientSum) {
        if (canDispense(clientSum)){
            createMapOfBanknotesForDispense(clientSum);
        } else {
            throw new IllegalArgumentException("This sum cann't be dispensed!");
        }
    }

    @Override
    public Integer getBalance() {
        return dispenser.entrySet().stream().mapToInt(entry -> entry.getKey().getNumericValue() * entry.getValue()).sum();
    }

    @Override
    public ATM clone() {
        Map<Nominal, Integer> dispenserCopy = new EnumMap<>(dispenser);
        return new ATMImpl(dispenserCopy);
    }

    @Override
    public Memento saveState() {
        return new Memento(clone());
    }

    public void restore(Map<Nominal, Integer> dispenserOld) {
        dispenser = dispenserOld;
    }

    private void createMapOfBanknotesForDispense(int clientSum) {
        int remainder = clientSum;
        Map<Nominal, Integer> mapForDispense = new EnumMap<>(Nominal.class);

        for (Nominal nom : dispenser.keySet()) {
            int value = nom.getNumericValue();
            int banknotesNumber = dispenser.get(nom);
            if (remainder >= value) {
                int banknotesToDraw = remainder / value;
                if (banknotesToDraw > banknotesNumber) {
                    banknotesToDraw = banknotesNumber;
                }
                mapForDispense.put(nom, banknotesToDraw);
                remainder -= banknotesToDraw * value;
            } else {
                mapForDispense.put(nom, 0);
            }
        }
        if (remainder == 0) {
            updateDispenser(mapForDispense);
        }
        else{
            throw new IllegalArgumentException("Please input another sum.");
        }
    }

    private void updateDispenser(Map<Nominal, Integer> mapForDispense) {
        Map<Nominal, Integer> tempDispenser = new EnumMap<>(Nominal.class);
        for(Nominal nom : dispenser.keySet()) {
            for (Nominal nominal : mapForDispense.keySet()) {
                if (nom.getNumericValue() == nominal.getNumericValue()) {
                    tempDispenser.put(nominal, dispenser.get(nom) - mapForDispense.get(nominal));
                }
            }
        }
        this.dispenser = tempDispenser;
    }

    private boolean canDispense(int clientSum) {
        return clientSum <= getBalance();
    }
}