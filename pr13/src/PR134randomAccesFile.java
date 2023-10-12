import java.io.RandomAccessFile;

public class PR134randomAccesFile {
    private static final int idSize = 4; // bytes
    private static final int noteSize = 4; // bytes
    private static final int charSize = 2; // bytes per caràcter en UTF-16
    private static final int nameSize = 20; // Longitud màxima en caràcters del nom

    public static void main(String[] args) {

        try (RandomAccessFile raf = new RandomAccessFile("./lib/data/PR134estudiants.dat", "rw")) {

            boolean running = true;

            int searchID = 0;

            String newName = "";

            float newNote = 0;
            
            String menu = "Escull una opcio\n";
            menu += "0) Afegeix Estudiant\n";
            menu += "1) Consultar Nota\n";
            menu += "2) Actualitzar Nota\n";
            menu += "3) Cerrar";

            while (running) {
                System.out.println(menu);

                int opcio = Integer.valueOf(Main.llegirLinia("Opció: "));

                switch(opcio) {
                    case 0:
                        newName = Main.llegirLinia("Nom: ");
                        newNote = Float.valueOf(Main.llegirLinia("Nota: "));

                        afegirEstudiant(raf, Math.toIntExact(raf.length()/48 + 1), newName, newNote);
                        System.out.println("Alumne afegit correctament\n");
                        break;

                    case 1: 
                        searchID = Integer.valueOf(Main.llegirLinia("Id del alumne: "));
                        if (searchID >= 1 & searchID <= Math.toIntExact(raf.length()/48)) {
                            mostrarEstudiant(raf, searchID);
                        }

                        else System.out.println("El ID que busques no existeix");
                        break;

                    case 2: 
                        searchID = Integer.valueOf(Main.llegirLinia("Id del alumne: "));
                        newNote = Float.valueOf(Main.llegirLinia("Nova nota: "));

                        if (searchID >= 1 & searchID <= Math.toIntExact(raf.length()/48)) {
                            actualitzarNotaEstudiant(raf, searchID, newNote);
                            System.out.println("S'ha modificat l'alumne correctament\n");
                        }

                        else System.out.println("El ID que busques no existeix\n");
                        break;

                    case 3: running = false; break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afegirEstudiant(RandomAccessFile raf, int id, String nom, float nota) throws Exception {
        raf.seek(raf.length());
        raf.writeInt(id);
        raf.writeChars(getPaddedName(nom));
        raf.writeFloat(nota);
    }

    public static String consultarEstudiant(RandomAccessFile raf, int id) throws Exception {
        raf.seek(getSeekPosition(id));
        raf.readInt();
        char[] chars = new char[nameSize];
        for (int i = 0; i < nameSize; i++) {
            chars[i] = raf.readChar();
        }
        return "Nom: " + new String(chars).trim() + " Nota: " + raf.readFloat() + "\n";
    }

    public static void actualitzarNotaEstudiant(RandomAccessFile raf, int id, float novaNota) throws Exception {
        raf.seek(getSeekPosition(id) + idSize + nameSize * charSize);
        raf.writeFloat(novaNota);
    }

    public static void mostrarEstudiant(RandomAccessFile raf, int id) throws Exception {
        System.out.println(id + ": " + consultarEstudiant(raf, id));
    }

    private static long getSeekPosition(int id) {
        return (id - 1) * (idSize + nameSize * charSize + noteSize);
    }

    private static String getPaddedName(String name) {
        if (name.length() > nameSize) {
            return name.substring(0, nameSize);
        }
        return String.format("%1$-" + nameSize + "s", name);
    }

}
