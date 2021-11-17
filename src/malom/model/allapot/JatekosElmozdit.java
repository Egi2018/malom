package malom.model.allapot;

import malom.model.JatekElem;
import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.ArrayList;
/** Ez az osztály az Allapot osztály leszármazottja és azért fele, hogy abban a fázisban amikor a játékos elmozgathat korongokat a pályán, ez a folyamat gördülékenyen történjen. */
public class JatekosElmozdit extends Allapot{
/** Az JatekosElmozdit osztály konstruktora ami a pályát és a játékos számot állítja be. */
    public JatekosElmozdit(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }
/** Eldönti egy adott pozicióról, hogy szabad pozició-e. */
    @Override
    public boolean szabadE(Pozicio pozicio) {
        return szomszedosSzabadCellak(palya.getIndulasiPozicio()).contains(pozicio);
    }
/** Ez a metódus felel az adott fázisokban a feladatok végre hajtásáért. Itt eldönti a függvény, hogy malmot alkotnanának-e az elmozgatás következtében a korongok és ennek megfelelően hív meg egy következő éllapotot, ami lehet az, hogy leveszünk egy korongot, vagy az, hogy a másik játékos kijelölheti, melyik korongal kíván lépni.*/
    @Override
    public void vegrehajt(Pozicio cel) {
        this.mozgat(cel);
        palya.setIndulasiPozicio(null);
        palya.setIndulasiPozicioSzomszedok(new ArrayList<>());
        if(palya.malomE(cel))
            this.palya.setAllapot(new JatekosLevesz(palya, jatekosSzam));
        else
            this.palya.setAllapot(new JatekosKijelol(palya, (++jatekosSzam) % 2));
    }
/** A kiválasztott korongot áthelyezi az általunk kiválasztott pozícióra. */
    private void mozgat(Pozicio cel){
        if(!palya.getMezo(palya.getIndulasiPozicio()).ures() && palya.getMezo(cel).ures()){
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(palya.getIndulasiPozicio()));
            palya.setMezo(palya.getIndulasiPozicio(), seged);
        }
    }
}
