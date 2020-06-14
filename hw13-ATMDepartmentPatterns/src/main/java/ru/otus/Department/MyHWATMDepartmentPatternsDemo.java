package ru.otus.Department;

import ru.otus.ATMs.*;

public class MyHWATMDepartmentPatternsDemo {
    public static void main(String[] args) {
        Department department = new DepartmentATM();
        DispenserBuilder dispenser1 = new DispenserBuilderImpl();
        dispenser1.addBanknotes(Nominal.ONE_HUNDRED,40)
                .addBanknotes(Nominal.FIVE_HUNDREDS,45)
                .addBanknotes(Nominal.ONE_THOUSAND,30)
                .addBanknotes(Nominal.TWO_THOUSANDS,35)
                .addBanknotes(Nominal.FIVE_THOUSANDS,40);
        ATM atm1 = dispenser1.getNewATM();
        department.addATM(atm1);
        System.out.println(department.getCurrentBalance());

        DispenserBuilder dispenser2 = new DispenserBuilderImpl();
        dispenser2.addBanknotes(Nominal.ONE_HUNDRED,10)
                .addBanknotes(Nominal.FIVE_HUNDREDS,15)
                .addBanknotes(Nominal.ONE_THOUSAND,10)
                .addBanknotes(Nominal.TWO_THOUSANDS,15)
                .addBanknotes(Nominal.FIVE_THOUSANDS,10);
        ATM atm2 = dispenser2.getNewATM();
        department.addATM(atm2);
        System.out.println(department.getCurrentBalance());

        atm1.dispense(55500);
        atm2.dispense(5100);
        System.out.println(department.getCurrentBalance());

        department.restore();
        System.out.println(department.getCurrentBalance());
    }
}
