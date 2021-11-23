package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;

public class JatekosLeveszMozgatasFazisban extends JatekosAllapot {

    public JatekosLeveszMozgatasFazisban(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return !palya.jatekosSzinEgyezikMezonLevoKoronggal(pozicio)
                && !palya.getMezo(pozicio).uresE();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        if (nemMalomVagyEllenfel3Korong(pozicio)) palya.lehelyezJatekElem(pozicio, new Ures());
        if (nyert()) palya.jatekVege();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (!nemMalomVagyEllenfel3Korong(pozicio)) return;
        this.palya.setJatekosAllapot(new JatekosKijelol(palya));
        palya.valtJatekos();
    }

    private boolean nemMalomVagyEllenfel3Korong(Pozicio pozicio) {
        return (palya.malomE(pozicio) && getKorongSzam() == 3)
                || !palya.malomE(pozicio);
    }
}
