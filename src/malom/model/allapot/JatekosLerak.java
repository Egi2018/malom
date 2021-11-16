package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosLerak extends Allapot{

    public JatekosLerak(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).ures();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        this.palya.setJatekElem(pozicio, palya.getJatekos(jatekosSzam));
        palya.novelKorSzam();
        if(palya.malomE(pozicio))
            this.palya.setAllapot(new JatekosLevesz(palya, jatekosSzam));
        else if(palya.getKorSzamlalo() == MAX_KORSZAM)
            this.palya.setAllapot(new JatekosKijelol(palya, (++jatekosSzam) % 2));
        else
            this.palya.setAllapot(new JatekosLerak(palya, (++jatekosSzam) % 2));
    }
}
