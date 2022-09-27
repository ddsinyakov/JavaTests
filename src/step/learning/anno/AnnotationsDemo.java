package step.learning.anno;

import jdk.jshell.spi.ExecutionControlProvider;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationsDemo {

    @FieldAnnotation(value = "For all versions", priority = -1)
    private final String separator = "-----------------------------------------------------------------------------------------";

    @MethodAnnotation("Entry point")
    public void run() {

        // region Get types
        Class<?> type = ClassWithAnnotation.class;
        Class<?> thisType = this.getClass();

        Class<?> nameType;
        try {
            nameType = Class.forName(
                    "step.learning.anno.ClassWithoutAnnotation"
            );
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Class not found: " + ex.getMessage());
            return;
        }

        // endregion

        // region Check class annotation

        // type
        MarkerAnnotation marker = type.getAnnotation(MarkerAnnotation.class);
        if(marker != null)
            System.out.printf("Class '%s' has MarkerAnnotation%n", type.getName());
        else
            System.out.printf("Class '%s' has no MarkerAnnotation%n", type.getName());

        // thisType
        marker = thisType.getAnnotation(MarkerAnnotation.class);
        if(marker != null)
            System.out.printf("Class '%s' has MarkerAnnotation%n", type.getName());
        else
            System.out.printf("Class '%s' has no MarkerAnnotation%n", type.getName());

        // nameType
        marker = nameType.getAnnotation(MarkerAnnotation.class);
        if(marker != null)
            System.out.printf("Class '%s' has MarkerAnnotation%n", type.getName());
        else
            System.out.printf("Class '%s' has no MarkerAnnotation%n", type.getName());

        // endregion

        // region Check methods annotation

//        scanClassMethods(type);
//        scanClassMethods(thisType);
//        scanClassMethods(nameType);

        // endregion

        // region Check field annotation

        scanClassField(type);
        scanClassField(thisType);
        scanClassField(nameType);

        // endregion
    }

    public void scanClassMethods(Class <?> type) {
        System.out.println(separator);
        Method[] methods = type.getDeclaredMethods();

        Object obj;
        try { obj = type.getDeclaredConstructor().newInstance(); }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        for (Method method : methods) {
            if(method.isAnnotationPresent(MethodAnnotation.class)) {
                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
                System.out.printf("Method '%s' of class '%s' does have '%s' method annotation %n",
                        method.getName(), type.getName(), methodAnnotation.value());

                try {
                    if(methodAnnotation.value().equals("Recommended") ) {
                        method.setAccessible(true);
                        method.invoke(obj);
                    } else if (methodAnnotation.value().equals("Deprecated")) {
                        System.out.println("Method is deprecated");
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }
            else {
                System.out.printf("Method '%s' of class '%s' does not have method annotation %n",
                        method.getName(), type.getName());
            }
        }
    }

    public void scanClassField(Class <?> type) {
        System.out.println(separator);
        Field[] fields = type.getDeclaredFields();

        Object obj = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldAnnotation.class)) {
                FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                System.out.printf("Field '%s' of class '%s' is '%s' with priority %d%n",
                        field.getName(), type.getName(), fieldAnnotation.value(), fieldAnnotation.priority());

                if (obj == null) {
                    try {
                        obj = type.getDeclaredConstructor().newInstance();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        return;
                    }
                }

                try {
                    field.setAccessible(true);
                    System.out.printf("Name: %s; value: %s%n", field.getName(), field.get(obj));
                }
                catch (IllegalAccessException ex) {
                    System.out.println(ex.getMessage());
                }

            }
            else {
                System.out.printf("Field '%s' of class '%s' has no annotation",
                        field.getName(), type.getName());
            }
        }
    }
}
