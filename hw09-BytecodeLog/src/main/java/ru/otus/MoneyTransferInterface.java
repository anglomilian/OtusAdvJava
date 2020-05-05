package ru.otus;

public interface MoneyTransferInterface {

    void transferToBelarusAccount(int bMoney);

    void transferToSwissAccount(int sMoney);

    void transferToRussianAccount(int rMoney);

    void transferToRussianAccount(double rMoney);

    void transferToCyprusAccount(int cMoney);
}
