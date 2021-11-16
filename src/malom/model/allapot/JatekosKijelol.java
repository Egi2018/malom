package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;

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
