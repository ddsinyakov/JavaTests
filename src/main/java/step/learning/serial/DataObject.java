package step.learning.serial;

import java.io.Serializable;

public class DataObject implements Serializable {
    private int privateField;
    protected float protectedField;
    public String publicField;
    private transient String transField;

    public DataObject(Object... args) {
        privateField   = args.length > 0 ? (int) args[0] : -1;
        protectedField = args.length > 1 ? (float) args[1] : -1;
        publicField    = args.length > 2 ? (String) args[2] : "-";
        transField     = args.length > 3 ? (String) args[3] : "-";
    }

    @Override
    public String toString() {
        return String.format("{pri:'%d', pro:'%f', pub:'%s', trans:'%s'}",
                privateField, protectedField, publicField, transField);
    }
}
