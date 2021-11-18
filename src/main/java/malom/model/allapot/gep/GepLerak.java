package malom.model.allapot.gep;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.jatekos.JatekosAllapot;
import malom.model.allapot.jatekos.JatekosKijelol;
import malom.model.allapot.jatekos.JatekosLevesz;

public class GepLerak extends GepAllapot{

    public GepLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt() {
        this.palya.setJatekElem(new Pozicio(0, 0), palya.getJatekos().getJatekElem());
        palya.novelKorSzam();
    }
}
