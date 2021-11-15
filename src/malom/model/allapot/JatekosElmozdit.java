package malom.model.allapot;

import malom.model.JatekElem;
import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosElmozdit extends Allapot{

    public JatekosElmozdit(MalomModel malomModel) {
        super(malomModel);
    }

    @Override
    public void vegrehajt(Pozicio cel) {
        this.mozgat(cel);
        this.palya.setAllapot(new JatekosKijelol(palya));
        palya.setIndulasiPozicio(null);
    }

    private void mozgat(Pozicio cel){
        if(!palya.getMezo(palya.getIndulasiPozicio()).ures() && palya.getMezo(cel).ures()){
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(palya.getIndulasiPozicio()));
            palya.setMezo(palya.getIndulasiPozicio(), seged);
        }
    }
}
