package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;

public class JatekosLeveszLerakFazisban extends Allapot {

    public JatekosLeveszLerakFazisban(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return !palya.jatekosSzinEgyezikMezonLevoKoronggal(pozicio)
                && !palya.getMezo(pozicio).uresE();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        if (!malom(pozicio)) palya.lehelyezJatekElem(pozicio, new Ures());
        // nincs elvi lehetősége, hogy a lerakás fázis végén nyerjhenm bármelyik játékos 12 korong esetén
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (malom(pozicio)) return;
        //előfordulhat, hogy a lerakból jöttünk, de a krongok elfogytak emiatt a kijelölbe kell átmennni
        if(palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM) palya.setJatekosAllapot(new JatekosKijelol(palya));
         else palya.setJatekosAllapot(new JatekosLerak(palya));
        palya.valtJatekos();
    }

    private boolean malom(Pozicio pozicio) {
        return palya.malomE(pozicio);
    }
}
