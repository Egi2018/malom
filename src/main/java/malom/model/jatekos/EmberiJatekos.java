package malom.model.jatekos;

import malom.model.Pozicio;
import malom.model.allapot.JatekosAllapot;
import malom.model.tabladolgai.JatekElem;

public class EmberiJatekos extends Jatekos{
    public EmberiJatekos(JatekosAllapot jatekosAllapot, JatekElem jatekElem) {
        super(jatekosAllapot, jatekElem);
    }

    @Override
    public void autoVegrehajt() {}

    @Override
    public void vegrehajt(Pozicio pozicio) {  //TODO beallitAllapot (neve) (lépés a játék menetben)
        if (jatekosAllapot.szabadE(pozicio)){
            jatekosAllapot.vegrehajt(pozicio);
            jatekosAllapot.setKovetkezoAllapot(pozicio);
        }
    }
}

