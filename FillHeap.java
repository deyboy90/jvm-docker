public class FillHeap {
    public static void main(String[] args) throws InterruptedException {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(String.format("Free Mem: %s, Total Memory: %s, Max Memory: %s", runtime.freeMemory() / mb,
                runtime.totalMemory() / mb, runtime.maxMemory() / mb));
        // long[] base = new long[(int) runtime.freeMemory()];
        try {
            while (true) {
                long[] additional = new long[10 * mb];
                System.out.println(String.format("Free Mem: %s, Total Memory: %s, Max Memory: %s", runtime.freeMemory() / mb,
                    runtime.totalMemory() / mb, runtime.maxMemory() / mb));
                Thread.sleep(500);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OOM Caught, praying for rescue ...");
        }
    }
}