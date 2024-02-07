package org.seydaliev;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DeadLockExample {
    private static class Resource {
        // Ресурсы
    }

    private final Resource resourceA = new Resource();
    private final Resource resourceB = new Resource();
    private final Semaphore semaphoreA = new Semaphore(1);
    private final Semaphore semaphoreB = new Semaphore(1);

    public void execute() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                acquireResourcesAndWork(semaphoreA, semaphoreB, resourceA, resourceB, "Thread-1");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                acquireResourcesAndWork(semaphoreA, semaphoreB, resourceA, resourceB, "Thread-2");
            }
        });

        thread1.start();
        thread2.start();
    }

    private void acquireResourcesAndWork(Semaphore semaphoreA, Semaphore semaphoreB, Resource firstResource, Resource secondResource, String threadName) {
        try {
            semaphoreA.acquire();
            System.out.println(threadName + " acquired " + firstResource);

            try {
                semaphoreB.acquire();
                System.out.println(threadName + " acquired " + secondResource);

                try {
                    // Имитация работы с ресурсом
                    TimeUnit.MILLISECONDS.sleep(100);
                } finally {
                    semaphoreB.release();
                    System.out.println(threadName + " released " + secondResource);
                }
            } finally {
                semaphoreA.release();
                System.out.println(threadName + " released " + firstResource);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
