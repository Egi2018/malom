package malom.model;

import lombok.Data;
import malom.model.allapot.jatekos.JatekosAllapot;

@Data
public class Jatekos {
    private JatekosAllapot jatekosAllapot;
    private JatekElem jatekElem;
    private int korSzamlalo;

    public Jatekos(JatekosAllapot jatekosAllapot, JatekElem jatekElem) {
        this.jatekosAllapot = jatekosAllapot;
        this.jatekElem = jatekElem;
        korSzamlalo = 0;
    }

    public void vegrehajt(Pozicio pozicio) {  //TODO beallitAllapot (neve) (lépés a játék menetben)
        if (jatekosAllapot.szabadE(pozicio)){
            jatekosAllapot.vegrehajt(pozicio);
            jatekosAllapot.setKovetkezoAllapot(pozicio);
        }
    }

    public void novelKorSzamlalo(){
        korSzamlalo++;
    }

}


