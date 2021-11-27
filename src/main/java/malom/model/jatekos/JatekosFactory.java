package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.tabladolgai.JatekElem;

/**
 * Ez az osztály felel azért, hogy az általunk kiválasztott mód után létrehozza a kiválasztott módnak megfelelő játékosokat.
 */
public class JatekosFactory {

    /**
     * Ez a metódus felel azért, hogy amikor a járék elindítását követően választunk játékmódot, akkor az a játékmód lépjen működésbe.
     *
     * @param tipus  az általunk kiválasztott játékmód.
     * @param palya  a malom pályája.
     * @param korong egy korong.
     * @return létrehoz, egy új játékost.
     */
    public static Jatekos letrehozJatekos(String tipus, MalomModel palya, JatekElem korong) {
        switch (tipus) {
            case "ember":
                return new EmberiJatekos(palya, korong);
            case "gep":
                return new GepiJatekos(palya, korong);
            default:
                return new EmberiJatekos(palya, korong);
        }
    }
}
