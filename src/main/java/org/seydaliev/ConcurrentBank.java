package org.seydaliev;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentBank {
    private final ConcurrentMap<Long, BankAccount> accounts;


    public ConcurrentBank() {
        accounts = new ConcurrentHashMap<>();
    }

    public BankAccount createAccount(long initialBalance) {
        BankAccount bankAccount = new BankAccount(initialBalance);
        accounts.putIfAbsent((long) bankAccount.hashCode(), bankAccount);
        return bankAccount;
    }

    public void transfer(BankAccount from, BankAccount to, long amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }

        synchronized (from) {
            synchronized (to) {
                long fromBalance = from.withdraw(amount);
                if (fromBalance >= amount) {
                    to.deposit(amount);
                } else {
                    from.deposit(amount);
                    throw new InsufficientFundsException("Insufficient funds on sender's account.");
                }
            }
        }
    }

    public long getTotalBalance() {
        return accounts.values().stream()
                .mapToLong(BankAccount::getBalance)
                .sum();
    }
}
