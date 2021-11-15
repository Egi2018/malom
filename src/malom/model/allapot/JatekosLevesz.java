package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.Ures;

public class JatekosLevesz extends Allapot{

    public JatekosLevesz(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        palya.setJatekElem(pozicio, new Ures());
        if(palya.getKorSzamlalo() >= MAX_KORSZAM)
            this.palya.setAllapot(new JatekosKijelol(palya, (++jatekosSzam) % 2));
        else
            this.palya.setAllapot(new JatekosLerak(palya, (++jatekosSzam) % 2));
    }
}
