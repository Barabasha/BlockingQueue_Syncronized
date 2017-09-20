import java.io.*;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private final Map<String, Integer> vowelsMap;
    private BlockingQueue<File> fileQueue = null;
    private File file;

    Consumer(BlockingQueue fileQueue, Map<String, Integer> vowelsMap) {
        this.fileQueue = fileQueue;
        this.vowelsMap = vowelsMap;
    }

    @Override
    public void run() {
        try {
            while (true) {
                file = fileQueue.take();
                String line;
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        synchronized (vowelsMap) {
                            if (vowelsMap.containsKey(String.valueOf(line.charAt(i)))) {
                                int count = vowelsMap.get(String.valueOf(line.charAt(i)));

                                vowelsMap.put(String.valueOf(line.charAt(i)), ++count);
                            }
                        }
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}



