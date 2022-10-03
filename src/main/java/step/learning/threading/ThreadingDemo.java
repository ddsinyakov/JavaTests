package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.Scanner;

@DemoClass(priority = 6)
public class ThreadingDemo {


    @EntryPoint
    public void run() {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();

        for(int i = 0; i < n; i++) {
            new ThreadWithId().setId(i).start();
        }
    }

    public void demo() {
        System.out.println("Threading demo");

        new PrinterThread().start();        // new thread
        new ArgThread()                     // new thread with args
                .setArg("Hello world")
                .start();

        new Thread () {                     // new anonymous thread
            @Override
            public void run() {
                System.out.println("Start Anonymous");
            }
        }.start();

        new Thread(new PrintRunnable())     // new thread with Runnable
                .start();

        new Thread(new Runnable() {         // new thread with Anonymous Runnable
            @Override
            public void run() {
                System.out.println("Start Anonymous Runnable");
            }
        }).start();

        Runnable runnable = () -> System.out.println("Start Runnable arrow");
        new Thread(runnable).start();       // new thread with Runnable arrow functor

        new Thread(this::printDemo)         // new thread with Runnable like other method
                .start();
    }

    private void printDemo() {
        System.out.println("Start Runnable with other method");
    }

    static class PrinterThread
            extends Thread{

        @Override
        public void run() {
            System.out.println("Start new thread");
        }
    }

    static class ArgThread
            extends Thread {

        private String arg;

        public ArgThread setArg(String arg) {
            this.arg = arg;
            return this;
        }

        public ArgThread() {
            this.arg = arg;
        }

        @Override
        public void run() {
            System.out.printf("Start with arguments '%s'%n", arg);
        }
    }

    static class PrintRunnable
            implements Runnable {

        @Override
        public void run() {
            System.out.println("Start Runnable");
        }
    }

    static class ThreadWithId
            extends Thread {

        private int id;

        public ThreadWithId setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public void run() {
            System.out.println("Run Thread with id " + id);
        }
    }
}
