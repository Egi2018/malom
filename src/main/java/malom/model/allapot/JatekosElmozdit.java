package malom.model.allapot;

import malom.model.JatekElem;
import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.ArrayList;

public class JatekosElmozdit extends Allapot{

    public JatekosElmozdit(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return szomszedosSzabadCellak(palya.getIndulasiPozicio()).contains(pozicio);
    }

    @Override
    public void vegrehajt(Pozicio cel) {
        this.mozgat(cel);
        palya.setIndulasiPozicio(null);
        palya.setIndulasiPozicioSzomszedok(new ArrayList<>());
    }

    @Override
    public void setKovetkezoAllapot(Pozicio cel) {
        if (palya.malomE(cel)){
            this.palya.setJatekosAllapot(new JatekosLevesz(palya));
        } else{
            this.palya.setJatekosAllapot(new JatekosKijelol(palya));
            palya.valtJatekos();
        }
    }

    private void mozgat(Pozicio cel){
        if(!palya.getMezo(palya.getIndulasiPozicio()).ures() && palya.getMezo(cel).ures()){
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(palya.getIndulasiPozicio()));
            palya.setMezo(palya.getIndulasiPozicio(), seged);
        }
    }
}
