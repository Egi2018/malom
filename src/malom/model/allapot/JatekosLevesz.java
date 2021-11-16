package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.Ures;

public class JatekosLevesz extends Allapot{

    public JatekosLevesz(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return !palya.getMezo(pozicio).equals(palya.getJatekos(jatekosSzam))
                && !palya.getMezo(pozicio).ures();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        palya.setJatekElem(pozicio, new Ures());
        if(nyert())

        if(palya.getKorSzamlalo() >= MAX_KORSZAM)
            this.palya.setAllapot(new JatekosKijelol(palya, (++jatekosSzam) % 2));
        else
            this.palya.setAllapot(new JatekosLerak(palya, (++jatekosSzam) % 2));
    }

    public boolean nyert() {
        int korongSzamlalo = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                if (palya.getJatekElem(new Pozicio(i, j)).equals(palya.getJatekos((++jatekosSzam) % 2))) korongSzamlalo++;
            }
        }
        return korongSzamlalo < 3;
    }
}
