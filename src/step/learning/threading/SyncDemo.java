package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

@DemoClass(priority = 7)
public class SyncDemo {

    @EntryPoint
    public void run() {

        SumCallable sumCounter = new SumCallable(100, 1.1);
        ExecutorService pool = Executors.newFixedThreadPool(5);

        Future<Double> result = null;
        for (int i = 0; i < 12; i++) {
            result = pool.submit(sumCounter);
        }

        try {
            System.out.println("Last value: " + result.get());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void demo() {
        System.out.println("Synchronization demo");

        int months = 12;
        threads = months;
        sum = 100;

        for (int i = 0; i < months; i++) {
            new Thread(plus10Percent).start();
        }

        // Thread pool
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            pool.submit((
                (Function<Integer, Runnable>) ( x ->
                    () -> System.out.println("pool works " + x)
                )
            ).apply(i));
        }

        pool.shutdown();
    }

    public static class SumCallable
            implements Callable<Double> {

        private double sum;
        private final double percent;
        private final Object sumLocker = new Object();

        public double getSum() {
            return sum;
        }

        public SumCallable(double sum, double percent) {
            this.sum = sum;
            this.percent = percent;
        }

        @Override
        public Double call() throws Exception {
            double tmp;
            synchronized (sumLocker) {
                sum *= percent;
                tmp = sum;
            }

            System.out.println("Current sum = " + tmp);
            return tmp;
        }
    }

    private int threads;
    private double sum;
    private final Object sumLocker = new Object();
    private final Runnable plus10Percent = () -> {
        double tmp;
        boolean isLast;
        synchronized (sumLocker) {
            sum *= 1.10;
            tmp = sum;
        }

        synchronized (sumLocker) {
            threads--;
            isLast = threads == 0;
        }

        System.out.println(isLast
                ? ConsoleColors.GREEN + "Current sum = " + tmp + ConsoleColors.RESET
                : "Current sum = " + tmp);
    };

}
