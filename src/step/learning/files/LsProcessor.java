package step.learning.files;

import java.io.File;

public class LsProcessor
        implements Processable {

    private final FileObserver observer; // observer object to get current position

    public LsProcessor(FileObserver obs) {
        observer = obs;
    }

    @Override
    public void runProcess(String data) {
        System.out.println("Run 'ls'");

        String[] list = observer.getCurrentDirectory().list(); // get list of files in current directory
        if(list != null){
            for(String itemName : list) {
                File temp = new File(itemName);
                if(temp.isDirectory()) System.out.print("D: ");
                else if(temp.isFile()) System.out.print("F: ");
                System.out.println(itemName);
            }
        } else {
            System.out.println("Directory is empty");
        }
    }
}
