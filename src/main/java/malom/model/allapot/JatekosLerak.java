package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosLerak extends Allapot{

    public JatekosLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).ures();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        this.palya.setJatekElem(pozicio, palya.getJatekos().getJatekElem());
        palya.novelKorSzam();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if(palya.malomE(pozicio)){
            this.palya.setJatekosAllapot(new JatekosLevesz(palya));
        } else if(palya.getJatekos().getKorSzamlalo() == MAX_KORSZAM){
            this.palya.setJatekosAllapot(new JatekosKijelol(palya));
            palya.valtJatekos();
        }else {
            palya.valtJatekos();
        }
    }
}
