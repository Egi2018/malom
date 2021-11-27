package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;

/**
 * A játék első fázisában ha malmunk keletkezik, akkor ez az osztály felelős az ekkor elvégzendő müveletekért.
 */
public class JatekosLeveszLerakFazisban extends Allapot {
    /**
     * Az osztály konstruktora.
     *
     * @param palya a malom játék pályája.
     */
    public JatekosLeveszLerakFazisban(MalomModel palya) {
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
     * Levesz egy általunk kiválasztott korongot. (Üresre állítja)
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        if (!malom(pozicio)) palya.lehelyezJatekElem(pozicio, new Ures());
        // nincs elvi lehetősége, hogy a lerakás fázis végén nyerjhenm bármelyik játékos 12 korong esetén
    }

    /**
     * Beállítja a következő állapotot attól függően, hogy épp elfogytak-e a lerakandó korongok vagy nem.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (malom(pozicio)) return;
        //előfordulhat, hogy a lerakból jöttünk, de a krongok elfogytak emiatt a kijelölbe kell átmennni
        if (palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM) palya.setJatekosAllapot(new JatekosKijelol(palya));
        else palya.setJatekosAllapot(new JatekosLerak(palya));
        palya.valtJatekos();
    }

    /**
     * Ez a metódus meghív egy másik metódust, ami elndönti a megadott pozícióról, hogy malom-e.
     *
     * @param pozicio egy pozíció.
     * @return meghívja azt a metódust ami eldönti, hogy malom-e.
     */
    private boolean malom(Pozicio pozicio) {
        return palya.malomE(pozicio);
    }
}
