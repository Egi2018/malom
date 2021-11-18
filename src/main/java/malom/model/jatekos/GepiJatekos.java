package malom.model.jatekos;

import malom.model.Pozicio;
import malom.model.allapot.JatekosAllapot;
import malom.model.tabladolgai.JatekElem;

public class GepiJatekos extends Jatekos{
    public GepiJatekos(JatekosAllapot jatekosAllapot, JatekElem jatekElem) {
        super(jatekosAllapot, jatekElem);
    }

    @Override
    public void autoVegrehajt() {
        if (jatekosAllapot.szabadE(null)){
            jatekosAllapot.vegrehajt(null);
            jatekosAllapot.setKovetkezoAllapot(null);
        }
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {}
}
