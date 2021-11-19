package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.JatekosAllapot;
import malom.model.allapot.JatekosLerak;
import malom.model.tabladolgai.JatekElem;

public class EmberiJatekos extends Jatekos{
    public EmberiJatekos(MalomModel palya, JatekElem jatekElem) {
        super(jatekElem);
        allapot = new JatekosLerak(palya);
    }

    @Override
    public void autoVegrehajt() {}

    @Override
    public void vegrehajt(Pozicio pozicio) {  //TODO beallitAllapot (neve) (lépés a játék menetben)
        if (allapot.szabadE(pozicio)){
            allapot.vegrehajt(pozicio);
            allapot.setKovetkezoAllapot(pozicio);
        }
    }
}

