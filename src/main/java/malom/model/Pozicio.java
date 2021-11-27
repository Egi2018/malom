package malom.model;

import lombok.Data;

/**
 * A táblán lévő pozíciók osztálya.
 */
@Data
public class Pozicio {
    private final int sor;
    private final int oszlop;

    /**
     * Az osztály konstruktora.
     * @param sor Pályán egy sor.
     * @param oszlop Pályán egy oszlop.
     */
    public Pozicio(int sor, int oszlop) {
        this.sor = sor;
        this.oszlop = oszlop;
    }

    /**
     * Új pozíciókat hoz létre.
     * @param sor Pályán egy sor.
     * @param oszlop Pályán egy oszlop.
     * @return Visszaadja a léterhozott pozíciót.
     */
    public static Pozicio letrehozUjPozicio(int sor, int oszlop){  //pozició létrehozó
        return new Pozicio(sor, oszlop);
    }
}
