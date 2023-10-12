import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PR132main {

    public static void main(String[] args) {

        PR132persona[] persones = new PR132persona[3];

        persones[0] = new PR132persona("Maria", "López", 36);
        persones[1] = new PR132persona("Gustavo", "Ponts", 63);
        persones[2] = new PR132persona("Irene", "Sales", 54);

        String file = "./lib/data/PR132people.ser";

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {

            // Escriptura del arxiu
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (PR132persona p : persones) {
                objectOutputStream.writeObject(p);
            }

            fileOutputStream.close();
            objectOutputStream.close();

            // Lectura del arxiu
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            
            PR132persona[] newPersones = new PR132persona[3];
            
            String taula = "*".repeat(70) + "\n" +
                            "*   *".repeat(14) + "\n" +
                            Main.padRight("|", 5) + Main.padRight("NOM", 15) + 
                            "|" + Main.padLeft("COGNOM", 15) + Main.padLeft("|", 15) + 
                            Main.padLeft("EDAT", 15) + Main.padLeft("|", 4) + "\n" +
                            "*".repeat(70) + "\n";

            for (int i = 0 ; i < newPersones.length ; i++) {
                newPersones[i] = (PR132persona) objectInputStream.readObject();
                taula += Main.padRight("|", 5) + Main.padRight(newPersones[i].getNom(), 15) + 
                        "|" + Main.padLeft(newPersones[i].getCognom(), 15) + Main.padLeft("|", 15) + 
                        Main.padLeft(((Integer)newPersones[i].getEdat()).toString(), 15) + Main.padLeft("|", 4) + "\n";
            }

            System.out.println(taula + "*".repeat(70));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}