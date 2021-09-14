package test;

import java.util.concurrent.atomic.AtomicInteger;

public class lock extends Thread {
    public static AtomicInteger v = new AtomicInteger();

    public static void main(String[] args) {
        new lock().start();
        new lock().start();

        while(true) {
            System.out.println(v);

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void add() {
        for(int i = 0; i < 100000; i++)
            v.addAndGet(1);
    }

    @Override
    public void run() {
        add();
    }
}
