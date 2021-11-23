package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.GepLerak;
import malom.model.tabladolgai.JatekElem;

public class GepiJatekos extends Jatekos{
    private MalomModel palya;
    public GepiJatekos(MalomModel palya, JatekElem jatekElem) {
        super(jatekElem);
        this.palya = palya;
        allapot = new GepLerak(palya);
    }

    @Override
    public void autoVegrehajt() {
        if (allapot.szabadE(null)){
            allapot.setKovetkezoAllapot(null);
            allapot.vegrehajt(null);
        }
        palya.valtJatekos();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {}
}
