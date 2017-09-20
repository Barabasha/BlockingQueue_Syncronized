import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private BlockingQueue<File> fileQueue;
    private File dir;

    Producer(BlockingQueue fileQueue, File dir) {
        this.fileQueue = fileQueue;
        this.dir = dir;
    }

    @Override
    public void run() {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File unit : files) {
                if (!unit.isDirectory()) {
                    try {
                        fileQueue.put(unit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
