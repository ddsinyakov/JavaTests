package step.learning.anno;

import java.util.Random;

@MarkerAnnotation
public class ClassWithAnnotation {

    @FieldAnnotation(value = "For version 1.0", priority = 1)
    public int field1;

    @FieldAnnotation(value = "For version 1.1", priority = 2)
    public String field2;

    public ClassWithAnnotation() {
        Random r = new Random();
        field1 = r.nextInt(0, 100);
        field2 = String.valueOf(r.nextInt(0, 100));
    }

    @MethodAnnotation("Deprecated")
    public void method1() {
        System.out.println(" --- method1 works --- ");
    }

    @MethodAnnotation("Recommended")
    public void method2() {
        System.out.println(" --- method2 works --- ");
    }

    @MethodAnnotation("Recommended")
    private void method3() {
        System.out.println(" --- method3 works --- ");
    }

}
