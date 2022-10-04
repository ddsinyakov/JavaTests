package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class App {
    public void run() {
        List<Application> demoClasses = getClasses();

        if (demoClasses == null) {
            System.out.println("Error searching for classes");
            return;
        }

        int input = -1;
        while (input != 0) {
            showMenu(demoClasses);
            input = getUserInput(demoClasses.size());

            if (input != 0)
                invoke(demoClasses, input - 1);
        }
    }

    public List<Application> getClasses() {
        Class<?> currentClass = Main.class;                         // get current class we work with
        String packageName = currentClass.getPackageName();         // get name of package current class is

        List<String> classNames = getClassNames(packageName);       // list of project classes names

        if (classNames == null){
            System.out.println("Error scanning package");
            return null;
        }

        // list of classes with DemoClass annotation
        List<Application> demoClasses = new ArrayList<>();
        for (String className : classNames) {                       // search for classes with DemoClass annotation
            // and add them to demoClasses
            Class<?> theClass;
            try { theClass = Class.forName(className); }
            catch (ClassNotFoundException ex) {
                System.out.println("Class not found: " + ex.getMessage());
                return null;
            }

            DemoClass anno = theClass.getAnnotation(DemoClass.class);
            if(anno != null) {
                demoClasses.add(
                        new Application()
                                .setAppClass(theClass)
                                .setPriority(anno.priority())
                );
            }
        }
        Collections.sort(demoClasses); // sort by priority

        return demoClasses;
    }

    public void addClassName(File file, String prefix, List<String> toAdd) {
        String fileName = file.getName();
        if (fileName.endsWith(".class")) {
            toAdd.add(
                    prefix + fileName.substring(0, fileName.lastIndexOf("."))
            );
        }
    }

    public List<String> getClassNames(String packageName) {

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

    public void showMenu(List<Application> demoClasses) {
        System.out.println("======================================================================================");
        int i = 1;
        for (Application theClass : demoClasses ) {
            System.out.printf("%d: %s%n", i++, theClass.getAppClass().getName());
        }
        System.out.println("0: Exit");
    }

    public int getUserInput(int size) {
        Scanner scanner = new Scanner(System.in);               // scanner to read user input
        int result = -1;

        while (result < 0 || result > size) {     // ask for index till user write valid one
            System.out.println("Make your choice");
            try { result = scanner.nextInt(); }
            catch (InputMismatchException ignored) {
                System.out.println("Incorrect choice");
            }
            scanner.nextLine();

            if (result < 0 || result > size)
                System.out.println("Invalid index");
        }

        if (result == 0) {
            System.out.println("Bye");
            return 0;
        }

        return result;
    }

    public void invoke(List<Application> demoClasses, int index) {
        Application app = demoClasses.get(index);                 // get chosen class type

        Object obj;                                               // get instance of chosen type
        try {                                                     // if instance already made use it, or make new one
            obj = app.getAppInstance();
            if (obj == null) {
                obj = app.getAppClass().getDeclaredConstructor().newInstance();
                app.setAppInstance(obj);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        Method[] methods = app.getAppClass().getMethods();        // get methods of chosen class and look for
        for(Method method : methods) {                            // method with EntryPoint annotation
            if(method.isAnnotationPresent(EntryPoint.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(obj);                           // invoke needed method
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
                break;
            }
        }
    }
}
