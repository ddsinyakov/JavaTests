package step.learning.serial;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@DemoClass
public class SerializationDemo {

    private final String fileName = "save.ser";
    @EntryPoint
    public void run(){

        serializeList();
        deserializeList();
    }

    public void serialize() {
        DataObject data1 = new DataObject();
        DataObject data2 = new DataObject(10);
        DataObject data3 = new DataObject(10, 20.5f);
        DataObject data4 = new DataObject(10, 20.5f, "Hello", "Transient");

        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
        System.out.println(data4);

        try (FileOutputStream file = new FileOutputStream(fileName)) {

            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(data1);
            oos.writeObject(data2);
            oos.writeObject(data3);
            oos.writeObject(data4);
            oos.flush();

        } catch (IOException ex) {
            System.out.println("Error serialization: " + ex.getMessage());
            return;
        }

        System.out.println("Serialized");
    }

    public void deserialize() {
        try (FileInputStream file = new FileInputStream(fileName)){

            ObjectInputStream ois = new ObjectInputStream(file);
            DataObject data;

            while(file.available() > 0) {
                data = (DataObject) ois.readObject();
                System.out.println(data);
            }

        } catch (Exception ex) {
            System.out.println("Error serialization: " + ex.getMessage());
            return;
        }
        System.out.println("Deserialized");
    }

    public void serializeList() {
        List<DataObject> list = new ArrayList<>();
        list.add(new DataObject(120, 20.5f, "Hello 1", "tra"));
        list.add(new DataObject(156, 23.8f, "Hello 2", "tra"));
        list.add(new DataObject(111, 23.7f, "Hello 3", "tra"));
        list.add(new DataObject(132, 25.1f, "Hello 4", "tra"));
        list.add(new DataObject(147, 29.1f, "Hello 5", "tra"));

        System.out.println("List:");
        for(DataObject obj : list)
            System.out.println(obj);

        try (FileOutputStream file = new FileOutputStream(fileName)) {

            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(list);
            oos.flush();

        } catch (IOException ex) {
            System.out.println("Error serialization: " + ex.getMessage());
            return;
        }

        System.out.println("Serialized");
    }

    public void deserializeList() {
        try (FileInputStream file = new FileInputStream(fileName)){

            ObjectInputStream ois = new ObjectInputStream(file);
            @SuppressWarnings("unchecked")
            List<DataObject> list = (List<DataObject>) ois.readObject();

            System.out.println("Deserialized List:");
            for(Object obj : list)
                if(obj instanceof DataObject)
                    System.out.println(obj);

        } catch (Exception ex) {
            System.out.println("Error serialization: " + ex.getMessage());
            return;
        }
        System.out.println("Deserialized");
    }
}
