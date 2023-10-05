import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PR131mainEscriu {
    public static void main(String[] args) {

        String file = "./lib/data/PR131HashMapData.ser";

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {

            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            PR131hashmap obj = new PR131hashmap();

            objectOutputStream.writeObject(obj);

            fileOutputStream.close();
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}