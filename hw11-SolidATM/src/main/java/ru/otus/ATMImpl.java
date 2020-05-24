package ru.otus;

import java.util.Set;
import java.util.TreeSet;

public class ATMImpl implements ATM {

    private Set<Cassette> dispenser = new TreeSet<>();

    @Override
    public void addToDispenser(Cassette cassette) {
        dispenser.add(cassette);
    }

    @Override
    public Set<Cassette> dispense(int clientSum) {
        if (canDispense(clientSum))
            return createSetOfBanknotesForDispense(clientSum);
        throw new IllegalArgumentException("This sum cann't be dispensed!");
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (Cassette cassette : dispenser) {
            balance += cassette.getValue().getNumericValue() * cassette.getBanknotesNumber();
        }
        return balance;
    }

    private Set<Cassette> createSetOfBanknotesForDispense(int clientSum) {
        int remainder = clientSum;
        Set<Cassette> setForDispense = new TreeSet<>();

        for (Cassette cassette : dispenser) {
            Currency cassetteValue = cassette.getValue();
            int value = cassetteValue.getNumericValue();
            int banknotesNumber = cassette.getBanknotesNumber();
            if (remainder >= value) {
                int banknotesToDraw = remainder / value;
                if (banknotesToDraw > banknotesNumber) {
                    banknotesToDraw = banknotesNumber;
                }
                setForDispense.add(new CassetteImpl(cassetteValue, banknotesToDraw));
                remainder -= banknotesToDraw * value;
            } else {
                setForDispense.add(new CassetteImpl(cassetteValue, 0));
            }
        }
        if (remainder == 0) {
            updateDispenser(setForDispense, dispenser);
            return setForDispense;
        }
        else{
            throw new IllegalArgumentException("Please input another sum.");
        }
    }

    private void updateDispenser(Set<Cassette> setForDispense, Set<Cassette> cassettes) {
        Set<Cassette>tempDispenser = new TreeSet<>();
        for(Cassette beforeDispense : cassettes) {
            for (Cassette dispensed : setForDispense) {
                if (beforeDispense.equals(dispensed)) {
                    tempDispenser.add(new CassetteImpl(beforeDispense.getValue(),
                            beforeDispense.getBanknotesNumber() - dispensed.getBanknotesNumber()));
                }
            }
        }
        this.dispenser = tempDispenser;
    }

    private boolean canDispense(int clientSum) {
        return clientSum <= getBalance();
    }
}