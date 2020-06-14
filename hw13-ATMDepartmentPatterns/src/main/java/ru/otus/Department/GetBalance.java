package ru.otus.Department;

import ru.otus.ATMs.*;

import java.util.List;

public class GetBalance implements Command {
    private final List<Listener> listeners;

    public GetBalance(List<Listener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public Integer execute() {
        int currentBalance = 0;
        for(Listener listener : listeners)
            currentBalance += listener.getBalance();
        return currentBalance;
    }
}