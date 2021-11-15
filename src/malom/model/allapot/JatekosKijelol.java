package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosKijelol extends Allapot{

    public JatekosKijelol(MalomModel malomModel) {
        super(malomModel);
    }

    @Override
    public void vegrehajt(Pozicio pozicio){
        this.palya.setIndulasiPozicio(pozicio);
        this.palya.setAllapot(new JatekosElmozdit(palya));
    }
}
