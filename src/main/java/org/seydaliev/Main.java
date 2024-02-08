package org.seydaliev;

import org.seydaliev.sinchronizers.ComplexTaskExecutor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> {
            try {
                bank.transfer(account1, account2, 200);
            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        });
        Thread transferThread2 = new Thread(() -> {
            try {
                bank.transfer(account2, account1, 100);
            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        });

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());

        System.out.println();
        System.out.println("DeadLockExample");

        DeadLockExample example = new DeadLockExample();
        example.execute();

        System.out.println();
        System.out.println("Sinchronizers");

        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5); // Количество задач для выполнения

        Runnable testRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " started the test.");

            // Выполнение задач
            taskExecutor.executeTasks(5);

            System.out.println(Thread.currentThread().getName() + " completed the test.");
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        taskExecutor.shutdown();
    }
}