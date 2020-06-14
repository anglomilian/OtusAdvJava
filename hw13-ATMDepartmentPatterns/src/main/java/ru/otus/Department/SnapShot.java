package ru.otus.Department;

import ru.otus.ATMs.*;

import java.util.*;

public class SnapShot {
    private final List<Memento> snapShotsList = new ArrayList<>();

    public void saveState(ATM atm) {
        snapShotsList.add(atm.saveState());
    }

    public List<ATM> restore() {
        List <ATM> list = new ArrayList<>();
        for(Memento memento : snapShotsList)
            list.add(memento.getAtm());
        return list;
    }
}