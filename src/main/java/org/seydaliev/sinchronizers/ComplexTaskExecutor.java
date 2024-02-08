package org.seydaliev.sinchronizers;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ComplexTaskExecutor {
    private final ExecutorService executor;

    public ComplexTaskExecutor(int numberOfThreads) {
        executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void executeTasks(int numberOfTasks) {
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks completed.");
        });

        for (int i =  1; i <= numberOfTasks; i++) {
            executor.submit(new ComplexTask(i, barrier));
        }
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
