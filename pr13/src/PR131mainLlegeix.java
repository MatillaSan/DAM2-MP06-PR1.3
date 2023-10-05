import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class PR131mainLlegeix {
    
    public static void main(String[] args) {

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {

            fileInputStream = new FileInputStream("./lib/data/PR131HashMapData.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);

            PR131hashmap obj = (PR131hashmap) objectInputStream.readObject();

            for (String key : obj.getMap().keySet()) {

                System.out.println(key + " - " + obj.getMap().get(key));

            }

            fileInputStream.close();
            objectInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
