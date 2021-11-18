package malom.model;

import lombok.Data;
import malom.model.allapot.Allapot;

@Data
public class Jatekos {
    private Allapot allapot;
    private JatekElem jatekElem;
    private int korSzamlalo;

    public Jatekos(Allapot allapot, JatekElem jatekElem) {
        this.allapot = allapot;
        this.jatekElem = jatekElem;
        korSzamlalo = 0;
    }

    public void vegrehajt(Pozicio pozicio) {  //TODO beallitAllapot (neve)
        if (allapot.szabadE(pozicio)){
            allapot.vegrehajt(pozicio);
            allapot.setKovetkezoAllapot(pozicio);
        }
    }

    public void novelKorSzamlalo(){
        korSzamlalo++;
    }
}


