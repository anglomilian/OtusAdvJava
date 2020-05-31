package ru.otus;

import java.util.Set;

public class MyHWSolidATMDemo {
    public static void main(String[] args) {

        ATM atm = new ATMImpl();
        atm.addToDispenser(Nominal.ONE_HUNDRED,40);
        atm.addToDispenser(Nominal.FIVE_HUNDREDS,45);
        atm.addToDispenser(Nominal.ONE_THOUSAND,30);
        atm.addToDispenser(Nominal.TWO_THOUSANDS,35);
        atm.addToDispenser(Nominal.FIVE_THOUSANDS,40);
        System.out.println("ATM balance: " + atm.getBalance());

        atm.dispense(83900);

        System.out.println("ATM balance after last dispense: " + atm.getBalance());

        atm.dispense(112700);

        System.out.println("ATM balance after last dispense: " + atm.getBalance());
    }
}
