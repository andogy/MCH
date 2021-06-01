package test.square;

public class Gc extends Thread{
    public void run() {
        Runtime.getRuntime().gc();
    }
}
