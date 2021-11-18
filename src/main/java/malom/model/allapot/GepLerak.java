package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class GepLerak extends JatekosAllapot{

    public GepLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        this.palya.setJatekElem(new Pozicio(0, 0), palya.getJatekos().getJatekElem());
        palya.novelKorSzam();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {

    }
}
