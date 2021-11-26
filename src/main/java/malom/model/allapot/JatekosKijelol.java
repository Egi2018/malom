package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosKijelol extends Allapot {


    public JatekosKijelol(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.jatekosSzinEgyezikMezonLevoKoronggal(pozicio)
                && !szomszedosSzabadCellak(pozicio).isEmpty();
    }

    @Override
    public void vegrehajt(Pozicio pozicio){
        palya.setIndulasiPozicio(pozicio);
        palya.setIndulasiPozicioSzomszedok(szomszedosSzabadCellak(pozicio));
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        this.palya.setJatekosAllapot(new JatekosElmozdit(palya));
    }
}
