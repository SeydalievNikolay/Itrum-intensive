package org.seydaliev;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);
        Thread producer = new Thread(()-> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Producer adding : " + i);
                    blockingQueue.enqueue(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer value = blockingQueue.dequeue();
                    System.out.println("Consumer removed : " + value);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}