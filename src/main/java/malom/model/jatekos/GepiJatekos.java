package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.GepLerak;
import malom.model.tabladolgai.JatekElem;

public class GepiJatekos extends Jatekos{
    public GepiJatekos(MalomModel palya, JatekElem jatekElem) {
        super(jatekElem);
        allapot = new GepLerak(palya);
    }

    @Override
    public void autoVegrehajt() {
        if (allapot.szabadE(null)){
            allapot.vegrehajt(null);
            allapot.setKovetkezoAllapot(null);
        }
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {}
}
