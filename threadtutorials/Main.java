package threadtutorials;

import threadtutorials.threads.MyThread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread.sleep(5000);

        myThread.stopThread();
    }
}
