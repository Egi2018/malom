package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

/**
 * JatekosLerak osztály a játékos korongjainak lerakás fázisának működtetéséért felel.
 */
public class JatekosLerak extends Allapot {
    /**
     * Az osztály kostruktora.
     *
     * @param palya a malom játék pályája.
     */
    public JatekosLerak(MalomModel palya) {
        super(palya);
    }

    /**
     * Eldönti egy adott pozicióról, hogy szabad pozició-e.
     *
     * @param pozicio ez egy pizicíó a pályán.
     * @return visszaadja, hogy lehetséges-e az adott lépés.
     */
    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).uresE();
    }

    /**
     * Ez a metódus lerak egy korongot egy üres pozícióra.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        palya.lehelyezJatekElem(pozicio, palya.getJatekosKorong());
        palya.novelKorSzam();
    }

    /**
     * Egy pozíció alapján eldönti melyik állapotba kell tovább kerülni-e a játékosnak.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (palya.malomE(pozicio)) {
            palya.setJatekosAllapot(new JatekosLeveszLerakFazisban(palya));
        } else if (palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM) {
            palya.setJatekosAllapot(new JatekosKijelol(palya));
            palya.valtJatekos();
        } else {
            palya.valtJatekos();
        }
    }
}
