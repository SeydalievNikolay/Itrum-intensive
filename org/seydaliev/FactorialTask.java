package org.seydaliev;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number == 0 || number == 1) {
            return 1L;
        } else {
            FactorialTask leftTask = new FactorialTask(number / 2);
            FactorialTask rightTask = new FactorialTask( number - number / 2);
            leftTask.fork();
            return leftTask.join() * rightTask.compute();
        }
    }
}
