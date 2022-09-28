package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

@DemoClass(priority = 1)
public class DataTypes {

    @EntryPoint
    public void run() {

        byte b = 127;
        System.out.print(ConsoleColors.RED);
        System.out.println(b + " (byte)");

        short s = 12345;
        System.out.print(ConsoleColors.GREEN);
        System.out.println(s + " (short)");

        int i = 1234567;
        System.out.print(ConsoleColors.YELLOW);
        System.out.println(i + " (int)");

        long l = 123456789L;
        System.out.print(ConsoleColors.BLACK);
        System.out.println(l + " (long)");

        float f = 1234.1234f;
        System.out.print(ConsoleColors.BLUE);
        System.out.println(f + " (float)");

        double d = 123456.123456d;
        System.out.print(ConsoleColors.PURPLE);
        System.out.println(d + " (double)");

        char c = 'A';
        System.out.print(ConsoleColors.CYAN);
        System.out.println(c + " (char)");

    }
}