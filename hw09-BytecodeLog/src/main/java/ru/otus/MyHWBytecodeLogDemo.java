package ru.otus;

public class MyHWBytecodeLogDemo {

    public static void main(String... args) throws NoSuchMethodException {
        MoneyTransferInterface testSecureLogger = SecureLogger.createClassLogger(MoneyTransferOperations.class);
        testSecureLogger.transferToBelarusAccount(10_000_000);
        testSecureLogger.transferToSwissAccount(5_000_000);
        //test Log for different method signature
        testSecureLogger.transferToRussianAccount(300);
        testSecureLogger.transferToRussianAccount(345.67);
        testSecureLogger.transferToCyprusAccount(3_000_000);
    }
}