package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.tabladolgai.JatekElem;

public class JatekosFactory {
    public static Jatekos letrehozJatekos(String tipus, MalomModel palya, JatekElem korong){
        switch(tipus){
            case "ember":
                return new EmberiJatekos(palya, korong);
            case "gep":
                return new GepiJatekos(palya, korong);
            default:
                return new EmberiJatekos(palya, korong);
        }
    }
}
