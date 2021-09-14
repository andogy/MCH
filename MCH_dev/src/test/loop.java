package test;

public class loop extends Thread {
    public void run() {
        while (true) {

        }
    }

    public static void main(String[] args) {
        for(int i = 12;i > 0;i--) {
            new loop().start();
        }
    }
}
