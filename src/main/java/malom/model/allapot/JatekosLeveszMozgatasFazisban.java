package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;

/**
 * A játék második fázisában ha malmunk keletkezik, akkor ez az osztály felelős az ekkor elvégzendő müveletekért.
 */
public class JatekosLeveszMozgatasFazisban extends Allapot {
    /**
     * Az osztály konstruktora.
     *
     * @param palya a malom játék pályája.
     */
    public JatekosLeveszMozgatasFazisban(MalomModel palya) {
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
        return !palya.jatekosSzinEgyezikMezonLevoKoronggal(pozicio)
                && !palya.getMezo(pozicio).uresE();
    }

    /**
     * Ürressé teszi az általunk megadott paraméteren a mezőt, és ha esetleg ezzel a levétellel 2-re csökkenne az ellenfél korongjainak a száma, abban az esetben meghí egy függvényt ami lefogja állítani a játékot.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        if (nemMalomVagyEllenfel3Korong(pozicio)) palya.lehelyezJatekElem(pozicio, new Ures());
        if (nyert()) palya.jatekVege();
    }

    /**
     * Beállítja a következő állapotot és játékost vált.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (!nemMalomVagyEllenfel3Korong(pozicio)) return;
        this.palya.setJatekosAllapot(new JatekosKijelol(palya));
        palya.valtJatekos();
    }

    /**
     * Egy adott pozícióra megvizsgálja, hogy lelehet-e venni. (megvizsgálja azt az eshetőséget amikor már csak 3 korongja van az ellenfélnek és az a 3 korong malmot alkot, illetve azt is, hogy malmot alkot e a kiválasztott korong)
     *
     * @param pozicio egy pályán lévő pozíció.
     * @return eldönti, hogy a feltétel teljesül-e.
     */
    private boolean nemMalomVagyEllenfel3Korong(Pozicio pozicio) {
        return (palya.malomE(pozicio) && getKorongSzam() == 3)
                || !palya.malomE(pozicio);
    }
}
