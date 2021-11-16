package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosKijelol extends Allapot{


    public JatekosKijelol(MalomModel palya, int jatekosSzam) {
        super(palya, jatekosSzam);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).equals(palya.getJatekos(jatekosSzam))
                && !szomszedosSzabadCellak(pozicio).isEmpty();
    }

    @Override
    public void vegrehajt(Pozicio pozicio){
        this.palya.setIndulasiPozicio(pozicio);
        palya.setIndulasiPozicioSzomszedok(szomszedosSzabadCellak(pozicio));
        this.palya.setAllapot(new JatekosElmozdit(palya, jatekosSzam));
    }
}
