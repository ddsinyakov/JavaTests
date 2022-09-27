package step.learning.files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CatProcessor
        implements Processable {

    private final FileObserver observer; // observer object to get current position

    public CatProcessor(FileObserver obs) {
        observer = obs;
    }

    @Override
    public void runProcess(String data) {

        File current = observer.getCurrentDirectory();
        File newFile = new File(current.getPath() + File.separator + data); // new file to open and read
        System.out.println("Run 'cat' with " + newFile.getPath());

        if (newFile.isDirectory())
            System.out.println("Can not open directory with 'cat'");
        else if (newFile.isFile()) {

            StringBuilder sb = new StringBuilder(); // builder to share file data

            try (FileReader reader = new FileReader(newFile.getPath())) {
                Scanner scan = new Scanner(reader); // read data from file
                sb.append(scan.nextLine());         // add data to builder
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }

            System.out.println(sb);

        }
        else
            System.out.println("File does not exist");
    }
}
