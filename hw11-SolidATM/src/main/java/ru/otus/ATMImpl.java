package ru.otus;

import java.util.Set;
import java.util.TreeSet;

public class ATMImpl implements ATM {

    private Set<Banknote> dispenser = new TreeSet<>();

    @Override
    public void addToDispenser(Banknote banknote) {
        dispenser.add(banknote);
    }

    @Override
    public Set<Banknote> dispense(int clientSum) {
        if (canDispense(clientSum))
            return createSetOfBanknotesForDispense(clientSum);
        throw new IllegalArgumentException("This sum cann't be dispensed!");
    }

    @Override
    public int getBalance() {
        int balance = 0;
        for (Banknote banknote : dispenser) {
            balance += banknote.getValue().getNumericValue() * banknote.getBanknotesNumber();
        }
        return balance;
    }

    private Set<Banknote> createSetOfBanknotesForDispense(int clientSum) {
        int remainder = clientSum;
        Set<Banknote> setForDispense = new TreeSet<>();

        for (Banknote banknote : dispenser) {
            Nominal banknoteValue = banknote.getValue();
            int value = banknoteValue.getNumericValue();
            int banknotesNumber = banknote.getBanknotesNumber();
            if (remainder >= value) {
                int banknotesToDraw = remainder / value;
                if (banknotesToDraw > banknotesNumber) {
                    banknotesToDraw = banknotesNumber;
                }
                setForDispense.add(new BanknoteImpl(banknoteValue, banknotesToDraw));
                remainder -= banknotesToDraw * value;
            } else {
                setForDispense.add(new BanknoteImpl(banknoteValue, 0));
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

    private void updateDispenser(Set<Banknote> setForDispense, Set<Banknote> banknotes) {
        Set<Banknote>tempDispenser = new TreeSet<>();
        for(Banknote beforeDispense : banknotes) {
            for (Banknote dispensed : setForDispense) {
                if (beforeDispense.equals(dispensed)) {
                    tempDispenser.add(new BanknoteImpl(beforeDispense.getValue(),
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