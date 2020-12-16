package threadtutorials.threads;

/**
 * @author Sepehr79
 * how to stop thread in java?
 * use a boolean variable like this class
 * see Main
 */
public class MyThread extends Thread {

    private boolean stop = false;

    public MyThread(){
        super();
    }

    @Override
    public void run() {
        System.out.println("Thread start...");
        int second = 0;
        while (!stop){
            System.out.println(second++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread stopped!");

    }

    public void stopThread(){
        stop = true;
    }

}
