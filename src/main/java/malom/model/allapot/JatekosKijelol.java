package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosKijelol extends JatekosAllapot {


    public JatekosKijelol(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).equals(palya.getJatekos().getJatekElem())
                && !szomszedosSzabadCellak(pozicio).isEmpty();
    }

    @Override
    public void vegrehajt(Pozicio pozicio){
        this.palya.setIndulasiPozicio(pozicio);
        palya.setIndulasiPozicioSzomszedok(szomszedosSzabadCellak(pozicio));
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        this.palya.setJatekosAllapot(new JatekosElmozdit(palya));
    }
}
