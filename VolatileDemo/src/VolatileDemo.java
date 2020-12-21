import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Evan
 */
public class VolatileDemo {
    static boolean stop = false;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i ++;

                // 1. 加了print 就会跳出线程
//                try {
//                    Thread.sleep(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                synchronized(VolatileDemo.class){

                }
            }


            System.out.println(i);
        });

        t.start();;
        t.join();
        Thread.sleep(1000);
        stop = true;

        ReentrantLock
    }
}
