package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // region GetClasses
        Class<?> currentClass = Main.class;                         // get current class we work with
        String packageName = currentClass.getPackageName();         // get name of package current class is

        List<String> classNames = getClassNames(packageName);       // list of project classes names

        if (classNames == null){
            System.out.println("Error scanning package");
            return;
        }


        List<Class<?>> demoClasses = new ArrayList<>();             // list of classes with DemoClass annotation
        for (String className : classNames) {                       // search for classes with DemoClass annotation
                                                                    // and add them to demoClasses
            Class<?> theClass;
            try { theClass = Class.forName(className); }
            catch (ClassNotFoundException ex) {
                System.out.println("Class not found: " + ex.getMessage());
                return;
            }

            if(theClass.isAnnotationPresent(DemoClass.class))
                demoClasses.add(theClass);
        }
        // endregion

        // region Menu
        int i = 1;
        for (Class<?> theClass : demoClasses ) {
            System.out.printf("%d: %s%n", i++, theClass.getName());
        }
        System.out.println("0: Exit");
        // endregion

        // region User Input

        Scanner scanner = new Scanner(System.in);               // scanner to read user input
        int result = -1;

        while (result < 0 || result > demoClasses.size()) {     // ask for index till user write valid one
            System.out.println("Make your choice");
            try { result = scanner.nextInt(); }
            catch (InputMismatchException ignored) {
                System.out.println("Incorrect choice");
            }
            scanner.nextLine();

            if (result < 0 || result > demoClasses.size())
                System.out.println("Invalid index");
        }

        if (result == 0) {
            System.out.println("Bye");
            return;
        }

        // endregion

        // region Look for EntryPoint method and invoke it

        int index = result - 1;
        Class<?> type = demoClasses.get(index);                 // get chosen class type

        Object obj;                                             // get instance of chosen type
        try { obj = type.getDeclaredConstructor().newInstance(); }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        Method[] methods = type.getMethods();                   // get methods of chosen class and look for
        for(Method method : methods) {                          // method with EntryPoint annotation
            if(method.isAnnotationPresent(EntryPoint.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(obj);                         // invoke needed method
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
                break;
            }
        }

        // endregion
    }

    // region getNames

    public static void addClassName(File file, String prefix, List<String> toAdd) {
        String fileName = file.getName();
        if (fileName.endsWith(".class")) {
            toAdd.add(
                    prefix + fileName.substring(0, fileName.lastIndexOf("."))
            );
        }
    }

    public static List<String> getClassNames(String packageName) {

        // loader to get class file
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL packageResource = classLoader.getResource(              // file resource
                packageName.replace(".", "/")       // replace . with /
        );

        if (packageResource == null) {                               // check if resource found correctly
            // System.out.println("Resource identification error");
            return null;
        }

        String packagePath = packageResource.getPath();              // get file path

        File packageBase = new File(packagePath);
        File[] list = packageBase.listFiles();

        if(list == null) {                                           // check if directory has files
            // System.out.println("Error scanning directory " + packageBase);
            return null;
        }

        List<String> classNames = new ArrayList<>();                 // list for names of classes that have .run

        for (File file : list) {                                     // go through files and directories
            // System.out.println((file.isFile() ? "file" : "dir") + ": " + file.getName());

            if (file.isFile()) {
                addClassName(file, packageName + ".", classNames);
            }

            if (file.isDirectory()) {
                File[] subList = file.listFiles();
                if(subList != null) {
                    for (File sub : subList) {
                        // System.out.println("    " + (sub.isFile() ? "file" : "dir") + ": " + sub.getName());
                        addClassName(sub, packageName + "." + file.getName() + ".", classNames);
                    }
                }
            }
        }

        return classNames;
    }

    // endregion
}