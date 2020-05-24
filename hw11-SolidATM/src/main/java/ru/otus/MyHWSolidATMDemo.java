package ru.otus;

import java.util.Set;

public class MyHWSolidATMDemo {
    public static void main(String[] args) {

        ATM atm = new ATMImpl();

        atm.addToDispenser(new CassetteImpl(Currency.FIVE_THOUSANDS, 40));
        atm.addToDispenser(new CassetteImpl(Currency.TWO_THOUSANDS, 35));
        atm.addToDispenser(new CassetteImpl(Currency.ONE_THOUSAND, 30));
        atm.addToDispenser(new CassetteImpl(Currency.FIVE_HUNDREDS, 35));
        atm.addToDispenser(new CassetteImpl(Currency.ONE_HUNDRED, 40));

        System.out.println("ATM balance: " + atm.getBalance());

        Set<Cassette> cassettes = atm.dispense(83900);
        System.out.println("Banknotes used to dispense client sum: ");
        for (Cassette cassette : cassettes)
            System.out.println(cassette.getValue() + ": " + cassette.getBanknotesNumber());

        System.out.println("ATM balance after last dispense: " + atm.getBalance());

        cassettes = atm.dispense(112700);
        System.out.println("Banknotes used to dispense client sum: ");
        for (Cassette cassette : cassettes)
            System.out.println(cassette.getValue() + ": " + cassette.getBanknotesNumber());

        System.out.println("ATM balance after last dispense: " + atm.getBalance());
    }
}
