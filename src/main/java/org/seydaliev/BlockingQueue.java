package org.seydaliev;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<E> {
    private final Queue<E> queue;
    private final int maxSize;

    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(E element) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized E dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E element = queue.remove();
        notifyAll();
        return element;
    }

    public synchronized int size() {
        return queue.size();
    }
}
