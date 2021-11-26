package malom.model.jatekos;

import lombok.Data;
import malom.model.Pozicio;
import malom.model.allapot.Allapot;
import malom.model.tabladolgai.JatekElem;

@Data
public abstract class Jatekos {
    protected Allapot allapot;
    protected JatekElem jatekElem;
    private int korSzamlalo;

    public Jatekos(JatekElem jatekElem) {
        this.jatekElem = jatekElem;
        korSzamlalo = 0;
    }

    public abstract void autoVegrehajt();

    public abstract void vegrehajt(Pozicio pozicio);

    public void novelKorSzamlalo(){
        korSzamlalo++;
    }
}


