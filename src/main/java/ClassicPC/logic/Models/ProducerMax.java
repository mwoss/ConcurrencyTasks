package ClassicPC.logic.Models;

import ClassicPC.logic.Utils.PCMonitorDeadlock;

import java.util.ArrayList;
import java.util.List;

public class ProducerMax extends Thread{
    private final PCMonitorDeadlock pcMonitor;
    private final int productsToProduceLimit;
    public int waiting = 0;

    public ProducerMax(PCMonitorDeadlock monitor, int buforLimit) {
        this.pcMonitor = monitor;
        this.productsToProduceLimit = buforLimit;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
//                System.out.println(Thread.currentThread().getName() + "MAX producing");
                List<Object> products = new ArrayList<>();
                for(int i = 0; i<this.productsToProduceLimit - 1; i++){
                    products.add(new Object());
                }
                pcMonitor.produce(products, this);
            }
            catch (InterruptedException e){
//                System.out.println(Thread.currentThread().getName()+ "MAX stopped");
                return;
            }
        }
    }
}
