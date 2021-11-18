package malom.model.allapot.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.jatekos.JatekosAllapot;
import malom.model.allapot.jatekos.JatekosKijelol;
import malom.model.allapot.jatekos.JatekosLevesz;

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
