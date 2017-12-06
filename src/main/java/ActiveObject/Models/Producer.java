package ActiveObject.Models;

import ActiveObject.Future.Future;
import ActiveObject.Proxy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {
    private Proxy proxy;
    private Random random;
    private int bufferLimit;
    private List<Future> taskList = new LinkedList<>();


    public Producer(Proxy proxy, int bufferLimit){
        this.proxy = proxy;
        this.random = new Random();
        this.bufferLimit = bufferLimit;
    }

    @Override
    public void run() {
        while(true){
            taskList.add(proxy.produce(random.nextInt(bufferLimit / 2) + 1));

            try{
                Thread.sleep(200); //give him some time to finish job
                for(Future task : taskList){
                    if(task.isFinished()){
                        task.getResult();
                        taskList.remove(task);
                    }
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
