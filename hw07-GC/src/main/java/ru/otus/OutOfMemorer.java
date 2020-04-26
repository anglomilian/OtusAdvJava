package ru.otus;

import java.util.ArrayList;

import static ru.otus.RandomString.*;

public class OutOfMemorer {
    private ArrayList <String> bigLibrary = new ArrayList<>();
    private final int ITEMS_TO_ADD = 10000;

    public void fillLabrary() throws InterruptedException {
        while (true){
            addBooks();
            removeBooks();
            Thread.sleep(20);
        }
    }

    private void addBooks(){
        for (int i = 0; i < ITEMS_TO_ADD; i++){
            bigLibrary.add(getRandomString(20));
        }
    }

    private void removeBooks(){
        for (int i = 0; i < ITEMS_TO_ADD / 4; i++) {
            bigLibrary.remove(i);
        }
    }
}