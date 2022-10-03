package step.learning.files;

import java.io.File;

public class FileObserver {

    private File currentDirectory; // current directory where user is

    /**
     * Getter for current directory
     * @return File with current directory
     */
    public File getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * Setter for current directory
     * @param currentDirectory new current directory
     * @throws IllegalArgumentException if file is not directory
     */
    public void setCurrentDirectory(File currentDirectory)
            throws IllegalArgumentException {

        if(currentDirectory.isDirectory())
            this.currentDirectory = currentDirectory;
        else throw new IllegalArgumentException("File is not directory!");
    }

    /**
     * Constructor for file system
     * @param initialPoint file system start point
     */
    public FileObserver(String initialPoint) {
        currentDirectory = new File(initialPoint);
    }
}
