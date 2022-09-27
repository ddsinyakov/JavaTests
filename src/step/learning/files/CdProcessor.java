package step.learning.files;

import java.io.File;

public class CdProcessor
        implements Processable{

    private final FileObserver observer; // observer object to get current position

    public CdProcessor(FileObserver obs) {
        observer = obs;
    }

    @Override
    public void runProcess(String data) {
        System.out.println("Run 'cd' with " + data);

        File current = observer.getCurrentDirectory();

        if(data.equals("..")) { // to previous directory
            String prevPath = current.getParent();

            if (prevPath != null)
                observer.setCurrentDirectory(new File(prevPath));
            else
                System.out.println("No previous directory");

            return;
        }

        File newFile = new File(current.getPath() + File.separator + data);
        if(newFile.isDirectory()) { // to next chosen directory
            observer.setCurrentDirectory(newFile);
        } else if (newFile.isFile()) {
            System.out.println("Try to open this file with 'cat'");
        } else {
            System.out.println("Directory does nor exist");
        }

    }
}
