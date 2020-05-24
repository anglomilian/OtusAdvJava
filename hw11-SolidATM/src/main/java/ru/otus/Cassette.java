package ru.otus;

public interface Cassette extends Comparable<Cassette> {
    Currency getValue();
    int getBanknotesNumber();
}