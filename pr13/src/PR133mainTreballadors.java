import java.util.Arrays;
import java.util.List;

public class PR133mainTreballadors {

    public static void main(String[] args) {

        boolean allCorrect = false; //Variable per saber si podem començar els cambis

        String file = "./lib/data/PR133treballadors.csv";
        List<String> csv = UtilsCSV.read(file);

        String[] titles = UtilsCSV.getKeys(csv);

        System.out.println(paintTable(csv, titles));

        String usrID = Main.llegirLinia("ID del trabajador: ");

        String[] idList = UtilsCSV.getColumnData(csv, titles[0]);
        idList = Arrays.copyOfRange(idList, 1, idList.length);

        int rowNumber = 0;

        for (String id : idList) {
            if (id.equals(usrID)) {
                // Guardo el numero de la fila
                rowNumber = UtilsCSV.getLineNumber(csv, titles[0], usrID);

                allCorrect = true;
                break;
            }
        }

        if (allCorrect) {

            String menu = "Elige el campo a modificar";
            menu += "\n0) Nom";
            menu += "\n1) Cognom";
            menu += "\n2) Departament";
            menu += "\n3) Salari";

            System.out.println(menu);
            int opcio = Integer.valueOf(Main.llegirLinia("Escribe el numero: "));

            String newValue = "";

            switch(opcio) {
                case 0: 
                    newValue = Main.llegirLinia("Escribe el nuevo nombre: ");
                    break;
                case 1: 
                    newValue = Main.llegirLinia("Escribe el nuevo apellido: ");
                    break;
                case 2: 
                    newValue = Main.llegirLinia("Escribe el nuevo departamento: ");
                    break;
                case 3: 
                    newValue = Main.llegirLinia("Escribe el nuevo salario: ");
                    break;
                default: System.out.println("Numero fuera de rango"); allCorrect = false; break;
            }

            if (allCorrect) {
                UtilsCSV.update(csv, rowNumber, titles[opcio + 1], newValue);
                UtilsCSV.write(file, csv);
            }

            System.out.println(paintTable(csv, titles));

        } else System.out.println("El ID es incorrecto");
    }

    // Metode per pintar la taula
    public static String paintTable(List<String> csvRows, String[] titles) {

        String tableHead = "*".repeat(20 * titles.length) + "\n" +
                            "*   *".repeat((20*titles.length)/5) + "\n";
        
        for (String title : titles) {
            tableHead += Main.padRight("|", 3) + Main.padRight(title, 15) + Main.padLeft("|", 2);
        }

        tableHead += "\n" + "*".repeat(20*titles.length) + "\n";

        String[] colum = UtilsCSV.getColumnData(csvRows, titles[0]); //Utilizare esta array para saber cuantas filas hay

        // Creo un for que haga una repeticion por cada fila
        for (int i = 1 ; i < colum.length ; i++) {
            String[] dataRow = UtilsCSV.getLineArray(csvRows.get(i)); //Guardo la info de la fila en un array

            //Creo un for para recorrer la lista
            for (String dr : dataRow) {
                tableHead += Main.padRight("|", 3) + Main.padRight(dr, 15) + Main.padLeft("|", 2);
            }

            tableHead += "\n";
        }

        tableHead += "*".repeat(20*titles.length);

        return tableHead;
    }
}
    

