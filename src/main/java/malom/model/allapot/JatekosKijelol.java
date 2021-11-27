package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

/**
 * Ez az osztály az Allapot osztály leszármazottja és azt az állapotot hivatott megtestesíteni, amikor rákattintunk valamire.
 */
public class JatekosKijelol extends Allapot {

    /**
     * Az osztály kostruktora.
     *
     * @param palya a malom játék pályája.
     */
    public JatekosKijelol(MalomModel palya) {
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
        return palya.jatekosSzinEgyezikMezonLevoKoronggal(pozicio)
                && !szomszedosSzabadCellak(pozicio).isEmpty();
    }

    /**
     * Ez a metódus felel az adott fázisokban a feladatok végre hajtásáért. Ebben az esetben beállítja az indulási pozíciót és meghívja a JatekosElmozdit állapotot.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        palya.setIndulasiPozicio(pozicio);
        palya.setIndulasiPozicioSzomszedok(szomszedosSzabadCellak(pozicio));
    }

    /**
     * Beállítja a következő állapoto.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        this.palya.setJatekosAllapot(new JatekosElmozdit(palya));
    }
}
