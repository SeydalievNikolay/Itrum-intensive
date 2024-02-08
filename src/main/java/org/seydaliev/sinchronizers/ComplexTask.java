package org.seydaliev.sinchronizers;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable{
    private final int taskNumber;
    private final CyclicBarrier barrier;
    private static final Random RANDOM = new Random();

    public ComplexTask(int taskNumber, CyclicBarrier barrier) {
        this.taskNumber = taskNumber;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int result = RANDOM.nextInt(100);
        System.out.println("Task " + taskNumber + " finished with result: " + result);

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
