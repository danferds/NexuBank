package com.finance.nexubank.observer;

import com.finance.nexubank.model.AbstractConta;
import java.util.ArrayList;
import java.util.List;

public class ContaNotifier {
    private final List<ContaObserver> observers = new ArrayList<>();

    public void addObserver(ContaObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(ContaObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ContaEvent event, AbstractConta conta) {
        for (ContaObserver observer : observers) {
            observer.update(event, conta);
        }
    }
}
