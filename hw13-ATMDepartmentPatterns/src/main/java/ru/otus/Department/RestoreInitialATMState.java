package ru.otus.Department;

import ru.otus.ATMs.*;

import java.util.ArrayList;
import java.util.List;

public class RestoreInitialATMState implements Command {
    private final SnapShot snapShot;

    public RestoreInitialATMState(SnapShot history) {
        this.snapShot = history;
    }

    @Override
    public List<ATM> execute() {
        return new ArrayList<>(snapShot.restore());
    }
}
