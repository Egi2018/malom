package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;

import java.util.ArrayList;

/**
 * Ez az osztály az Allapot osztály leszármazottja és azért fele, hogy abban a fázisban amikor a játékos elmozgathat korongokat a pályán, ez a folyamat gördülékenyen történjen.
 */
public class JatekosElmozdit extends Allapot {
    /**
     * Az JatekosElmozdit osztály konstruktora ami a pályát állítja be.
     *
     * @param palya a malom játék pályája.
     */
    public JatekosElmozdit(MalomModel palya) {
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
        return szomszedosSzabadCellak(palya.getIndulasiPozicio()).contains(pozicio);
    }

    /**
     * Ez a metódus felel az adott fázisokban a feladatok végre hajtásáért. Itt eldönti a függvény, hogy malmot alkotnanának-e az elmozgatás következtében a korongok és ennek megfelelően hív meg egy következő éllapotot, ami lehet az, hogy leveszünk egy korongot, vagy az, hogy a másik játékos kijelölheti, melyik korongal kíván lépni.
     *
     * @param cel ez egy cél pozíció.
     */
    @Override
    public void vegrehajt(Pozicio cel) {
        this.mozgat(cel);
        palya.setIndulasiPozicio(null);
        palya.setIndulasiPozicioSzomszedok(new ArrayList<>());
    }

    /**
     * Beállítja a következő éllapotot annak függvényében, hogy a korong elmozdítása következtében kelettkezett-e malom.
     *
     * @param cel ez az a pozíció amire elszeretnénk mozdítani a korongot.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio cel) {
        if (palya.malomE(cel)) {
            this.palya.setJatekosAllapot(new JatekosLeveszMozgatasFazisban(palya));
        } else {
            this.palya.setJatekosAllapot(new JatekosKijelol(palya));
            palya.valtJatekos();
        }
    }

    /**
     * A kiválasztott korongot áthelyezi az általunk kiválasztott pozícióra.
     *
     * @param cel a cél pozíció (általunk kiválasztott pozíció).
     */
    private void mozgat(Pozicio cel) {
        if (!palya.getMezo(palya.getIndulasiPozicio()).uresE() && palya.getMezo(cel).uresE()) {
            JatekElem celMezoKorong = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(palya.getIndulasiPozicio()));
            palya.setMezo(palya.getIndulasiPozicio(), celMezoKorong);
        }
    }
}
