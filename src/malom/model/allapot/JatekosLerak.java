package malom.model.allapot;

import malom.model.FeherKorong;
import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosLerak extends Allapot{
    private static final int MAX_KORSZAM = 3*2;

    public JatekosLerak(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        this.palya.setJatekElem(pozicio, palya.getJatekos(jatekosSzam));
        if(palya.getKorSzamlalo() == MAX_KORSZAM)
            this.palya.setAllapot(new JatekosKijelol(palya, (++jatekosSzam) % 2));
        else
            this.palya.setAllapot(new JatekosLerak(palya, (++jatekosSzam) % 2));
    }
}
