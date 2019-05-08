public class GenerateOOM {

    public static void main(String[] args) throws Exception {
        GenerateOOM memoryTest = new GenerateOOM();
        memoryTest.generateOOM();
    }

    public void generateOOM() throws Exception {
        int iteratorValue = 20;

        try {
            System.out.println("\n=================> OOM test started..\n");
            for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
                Runtime runtime = Runtime.getRuntime();
                int mb = 1024 * 1024;

                System.out.println(String.format("Iteration: %s, Free Mem: %s, Total Memory: %s, Max Memory: %s", outerIterator, runtime.freeMemory() / mb,
                        runtime.totalMemory() / mb, runtime.maxMemory() / mb));

                int loop1 = 2;
                int[] memoryFillIntVar = new int[iteratorValue];
                // fill memoryFillIntVar array in loop..
                do {
                    memoryFillIntVar[loop1] = 0;
                    loop1--;
                } while (loop1 > 0);
                iteratorValue = iteratorValue * 5;
                Thread.sleep(2000);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OOM Caught, praying for rescue ...");
            Thread.sleep(1000000);
        }
    }

}
