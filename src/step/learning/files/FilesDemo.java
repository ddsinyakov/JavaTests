package step.learning.files;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FilesDemo {
    public void run() {
        ioDemo();
    }

    // region File system

    /**
     * Work with file system
     */

    private void fsDemo() {
        File file = new File("Output"); // create file

        if (file.isDirectory()) { // if file is directory
            System.out.println(file.getName() + " - is existing directory");

            String[] list = file.list(); // list of files in directory
            if(list != null){ // show all files
                for(String itemName : list) {
                    System.out.println(itemName);
                }
            } else {
                System.out.println("List request error");
            }

            System.out.println("Do you want to delete directory?"); // ask for delete
            int sym;
            try {
                sym = System.in.read();
            } catch (IOException ex) {
                System.out.println("System error: " + ex.getMessage());
                return;
            }

            if(sym == 'y') {
                boolean res = file.delete();
                if (res) System.out.println("Directory deleted");
                else System.out.println("Deletion error");
            } else {
                System.out.println("Delete cancelled");
            }


        }
        else if (file.isFile()) { // if it is file
            System.out.println(file.getName() + " - is existing file");

            // show data
            if (file.canRead()) System.out.println("Readable");
            else System.out.println("Non-Readable");

            if (file.canExecute()) System.out.println("Executable");
            else System.out.println("Non-Executable");

            if (file.canWrite()) System.out.println("Writable");
            else System.out.println("Non-Writable");

            System.out.println("File size: " + file.length());

        }
        else { // file do not exist
            System.out.println(file.getName() + " - does not exist");
            boolean res = file.mkdir(); // create new directory

            if (res) {
                System.out.printf("Directory '%s' created%n", file.getName());
            } else {
                System.out.println("Directory creation error");
            }
        }
    }

    // endregion

    // region work with files

    /**
     * Save and read data from files
     */

    private void ioDemo() {
        StringBuilder sb = new StringBuilder();

        // try (FileReader reader = new FileReader("hello.txt")){
        //
        // }
        try (InputStream reader = new FileInputStream("hello.txt")) {
            int symbol;
            while((symbol = reader.read()) != -1) {
                sb.append((char) symbol);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        String fileContent = new String(
                sb.toString().getBytes(StandardCharsets.ISO_8859_1)
        );

        System.out.println(fileContent);

        try (FileWriter writer = new FileWriter("hello.txt")) {
            writer.write("Здоровенькі були!");
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Writing finished");
    }

    // endregion
}
