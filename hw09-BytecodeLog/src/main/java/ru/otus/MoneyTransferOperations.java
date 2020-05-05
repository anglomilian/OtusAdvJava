package ru.otus;

public class MoneyTransferOperations implements MoneyTransferInterface {

    public void transferToRussianAccount(int rMoney) {
        System.out.println("Transfer money to another Russian account in running. Nothing special.");
    }

    @Log
    public void transferToRussianAccount(double rMoney) {
        System.out.println("Transfer money to another Russian account in running. Checking another method signature.");
    }

    @Log
    public void transferToCyprusAccount(int cMoney) {
        System.out.println("Transfer money to Cyprus in running.");
    }

    public void transferToBelarusAccount(int cMoney) {
        System.out.println("Transfer money to Belarus in running.");
    }

    @Log
    public void transferToSwissAccount(int sMoney) {
        System.out.println("Transfer money to Swiss in running.");
    }
}