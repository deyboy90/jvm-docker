public class FillHeap {
    public static void main(String[] args) throws InterruptedException {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println(String.format("Free Mem: %s, Total Memory: %s, Max Memory: %s", runtime.freeMemory() / mb,
                runtime.totalMemory() / mb, runtime.maxMemory() / mb));

        try {
            int topUp = args.length != 0 ? Integer.valueOf(args[0]) * mb : 10 * mb;
            System.out.println(String.format("Using topup of: %s MB", topUp/mb));

            while (true) {
                int memoryToAdd = topUp;

                System.out.println(String.format("Free Mem: %s, Total Memory: %s, Max Memory: %s", runtime.freeMemory() / mb,
                        runtime.totalMemory() / mb, runtime.maxMemory() / mb));
                System.out.println(String.format("Adding memory: %s MB", memoryToAdd/mb));

                if (runtime.freeMemory() <= memoryToAdd) {
                    topUp -= 1 * mb;
                    continue;
                }

                if (memoryToAdd >= 0) {
                    long[] additional = new long[memoryToAdd];

                }
                topUp += 1 * mb; // increasing it in every loop
                Thread.sleep(500);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OOM Caught, praying for rescue ...");
        }

    }
}