package org.seydaliev;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
    private final AtomicLong balance;

    public BankAccount(long initialBalance) {
        balance = new AtomicLong(initialBalance);
    }

    public long deposit(long amount) {
        return balance.addAndGet(amount);
    }

    public long withdraw(long amount) {
        return balance.updateAndGet(bal -> bal >= amount ? bal -amount : bal);
    }

    public long getBalance() {
        return balance.get();
    }
}
