import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Vowels {
    public static void main(String[] args) throws InterruptedException, IOException {

        Map vowelsMap = new HashMap<String, Integer>();
        String[] arr = new String[]{"a", "e", "i", "o", "u", "y"};
        for (String anArr : arr) {
            vowelsMap.put(anArr, 0);
        }
        File dir = new File("C:\\test_hillel");
        BlockingQueue fileQueue = new ArrayBlockingQueue(10);
        Producer p = new Producer(fileQueue, dir);
        Consumer c1 = new Consumer(fileQueue, vowelsMap);
        Consumer c2 = new Consumer(fileQueue, vowelsMap);
        Consumer c3 = new Consumer(fileQueue, vowelsMap);
        Thread producer = new Thread(p);
        producer.start();
        Thread consumer1 = new Thread(c1);
        Thread consumer2 = new Thread(c2);
        Thread consumer3 = new Thread(c3);
        consumer1.start();
        consumer2.start();
        consumer3.start();
        producer.join();
        Thread.sleep(2000);
        consumer1.interrupt();
        consumer2.interrupt();
        consumer3.interrupt();
        System.out.println(vowelsMap);
    }
}
