package ClassicPC.logic.Models;

import ClassicPC.logic.Utils.PCMonitor;

import java.util.Random;

public class Consumer extends Thread {

    private final PCMonitor pcMonitor;
    private final int productToConsumeLimit;
    private final Random random;

    public Consumer(PCMonitor monitor, int limit) {
        this.pcMonitor = monitor;
        this.productToConsumeLimit = limit;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int productsToConsume = random.nextInt(productToConsumeLimit) + 1;
                pcMonitor.consume(productsToConsume);
            }
            catch (InterruptedException e){
//                System.out.println("Consumer thread stopped");
                return;
            }
        }
    }
}
