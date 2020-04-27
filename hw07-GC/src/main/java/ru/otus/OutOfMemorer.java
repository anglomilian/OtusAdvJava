package ru.otus;

import java.util.ArrayList;

import static ru.otus.RandomString.*;

public class OutOfMemorer {
    private ArrayList <Book> bigLibrary = new ArrayList<>();
    private final int ITEMS_TO_ADD = 10000;

    public void fillLabrary() throws InterruptedException {
        int i = 0;
        while (true){
            addBooks();
            removeBooks();
            Thread.sleep(50);
            System.out.println("Fill library counter: " + i++);
        }
    }

    private void addBooks(){
        for (int i = 0; i < ITEMS_TO_ADD; i++){
            //bigLibrary.add(getRandomString(20));
            bigLibrary.add(new Book(getRandomString(15), getRandomString(20), Math.random() * 100));
        }
    }

    private void removeBooks(){
        for (int i = 0; i < ITEMS_TO_ADD / 4; i++) {
            bigLibrary.remove(i);
        }
    }
}

class Book{
    private String name;
    private String author;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    Book(String n, String a, double p){
        name = n;
        author = a;
        price = p;
    }

    @Override
    public String toString(){
        return String.format("\tBook name: %s, Author: %s, Price: %.02f" + System.lineSeparator(), name, author, price);
    }
}
