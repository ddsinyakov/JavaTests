package step.learning.anno;

import java.util.Random;

public class ClassWithoutAnnotation {

    public int field1;
    public String field2;

    public ClassWithoutAnnotation() {
        Random r = new Random();
        field1 = r.nextInt(0, 100);
        field2 = String.valueOf(r.nextInt(0, 100));
    }

    public void method1() {
        System.out.println("method1 works");
    }
    public void method2() {
        System.out.println("method2 works");
    }
}
