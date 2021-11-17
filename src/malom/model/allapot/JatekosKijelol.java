package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
/** Ez az osztály az Allapot osztály leszármazottja és azt az állapotot hivatott megtestesíteni, amikor rákattintunk valamire. */
public class JatekosKijelol extends Allapot{


    public JatekosKijelol(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }
/** Eldönti egy adott pozicióról, hogy szabad pozició-e. */
    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).equals(palya.getJatekos(jatekosSzam))
                && !szomszedosSzabadCellak(pozicio).isEmpty();
    }
/** Ez a metódus felel az adott fázisokban a feladatok végre hajtásáért. Ebben az esetben beállítja az indulási pozíciót és meghívja a JatekosElmozdit állapotot. */
    @Override
    public void vegrehajt(Pozicio pozicio){
        this.palya.setIndulasiPozicio(pozicio);
        palya.setIndulasiPozicioSzomszedok(szomszedosSzabadCellak(pozicio));
        this.palya.setAllapot(new JatekosElmozdit(palya, jatekosSzam));
    }
}
