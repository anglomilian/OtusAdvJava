package ru.otus;

public class MyHWAnRefDemo {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        MyTestFW test1 = new MyTestFW("ru.otus.ClassWithTests");
        test1.testRunner();
    }
}
