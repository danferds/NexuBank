package com.finance.nexubank.observer;

import com.finance.nexubank.model.AbstractConta;

public interface ContaObserver {
    void update(ContaEvent event, AbstractConta conta);
}
