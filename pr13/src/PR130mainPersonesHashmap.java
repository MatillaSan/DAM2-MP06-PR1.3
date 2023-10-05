import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class PR130mainPersonesHashmap {
    public static void main(String[] args) {
        HashMap<String, Integer> personas = new HashMap<String, Integer>();

        personas.put("Gean1", 12);
        personas.put("Gean2", 15);
        personas.put("Gean3", 18);
        personas.put("Gean4", 21);
        personas.put("Gean69", 47);

        String file = "./lib/data/PR130persones.dat";

        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;

        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;

        try {

            fileOutputStream = new FileOutputStream(file);
            dataOutputStream = new DataOutputStream(fileOutputStream);

            for (String key : personas.keySet()) {
                dataOutputStream.writeUTF(key);
                dataOutputStream.writeInt(personas.get(key));
            }

            dataOutputStream.flush();

            fileOutputStream.close();
            dataOutputStream.close();

            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);

            while (dataInputStream.available() > 0) {
                System.out.println(dataInputStream.readUTF() + " - " + dataInputStream.readInt());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
