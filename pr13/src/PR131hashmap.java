import java.io.Serializable;
import java.util.HashMap;

public class PR131hashmap implements Serializable {

    public static void main(String[] args) {

        PR131mainEscriu.main(args);
        PR131mainLlegeix.main(args);

    }

    HashMap<String, String> map;
    
    public PR131hashmap() {
        map  = new HashMap<String, String>();

        map.put("Gean1", "elMejor");
        map.put("Gean2", "elSegundoMejor");
        map.put("Gean3", "elTerceroMejor");
        map.put("Gean4", "elCuartoMejor");
        map.put("Gean5", "elQuintoMejor");
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}