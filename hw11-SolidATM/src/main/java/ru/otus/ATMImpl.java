package ru.otus;

import java.util.*;

public class ATMImpl implements ATM {

    private Map<Nominal, Integer> dispenser = new EnumMap<>(Nominal.class);

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
    public int getBalance() {
        return dispenser.entrySet().stream().mapToInt(entry -> entry.getKey().getNumericValue() * entry.getValue()).sum();
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