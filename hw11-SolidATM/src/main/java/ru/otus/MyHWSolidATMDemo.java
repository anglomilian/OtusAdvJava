package ru.otus;

import java.util.Set;

public class MyHWSolidATMDemo {
    public static void main(String[] args) {

        ATM atm = new ATMImpl();

        atm.addToDispenser(new BanknoteImpl(Nominal.FIVE_THOUSANDS, 40));
        atm.addToDispenser(new BanknoteImpl(Nominal.TWO_THOUSANDS, 35));
        atm.addToDispenser(new BanknoteImpl(Nominal.ONE_THOUSAND, 30));
        atm.addToDispenser(new BanknoteImpl(Nominal.FIVE_HUNDREDS, 35));
        atm.addToDispenser(new BanknoteImpl(Nominal.ONE_HUNDRED, 40));

        System.out.println("ATM balance: " + atm.getBalance());

        Set<Banknote> banknotes = atm.dispense(83900);
        System.out.println("Banknotes used to dispense client sum: ");
        for (Banknote banknote : banknotes)
            System.out.println(banknote.getValue() + ": " + banknote.getBanknotesNumber());

        System.out.println("ATM balance after last dispense: " + atm.getBalance());

        banknotes = atm.dispense(112700);
        System.out.println("Banknotes used to dispense client sum: ");
        for (Banknote banknote : banknotes)
            System.out.println(banknote.getValue() + ": " + banknote.getBanknotesNumber());

        System.out.println("ATM balance after last dispense: " + atm.getBalance());
    }
}
