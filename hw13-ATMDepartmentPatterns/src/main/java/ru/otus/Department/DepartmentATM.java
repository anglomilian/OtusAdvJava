package ru.otus.Department;

import ru.otus.ATMs.*;

import java.util.ArrayList;
import java.util.List;

public class DepartmentATM implements Department{
    private List<ATM> atmList = new ArrayList<>();
    private List<Listener> listeners = new ArrayList<>();
    private final SnapShot snapShot = new SnapShot();

    @Override
    public void addATM(ATM atm) {
        saveState(atm);
        atmList.add(atm);
        subscribeATM(atm);
    }

    public void subscribeATM(ATM atm) {
        listeners.add(atm);
    }

    public void unsubscribeATM(ATM atm) {
        listeners.remove(atm);
    }

    @Override
    public void saveState(ATM atm) {
        snapShot.saveState(atm);
    }

    @Override
    public void restore() {
        atmList = new RestoreInitialATMState(snapShot).execute();
        listeners = new ArrayList<>(atmList);
    }

    @Override
    public int getCurrentBalance() {
        return new GetBalance(listeners).execute();
    }
}
