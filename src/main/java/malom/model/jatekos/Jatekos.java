package malom.model.jatekos;

import lombok.Data;
import malom.model.Pozicio;
import malom.model.allapot.JatekosAllapot;
import malom.model.tabladolgai.JatekElem;

@Data
public abstract class Jatekos {
    protected JatekosAllapot jatekosAllapot;
    private JatekElem jatekElem;
    private int korSzamlalo;

    public Jatekos(JatekosAllapot jatekosAllapot, JatekElem jatekElem) {
        this.jatekosAllapot = jatekosAllapot;
        this.jatekElem = jatekElem;
        korSzamlalo = 0;
    }

    public abstract void autoVegrehajt();

    public abstract void vegrehajt(Pozicio pozicio);

    public void novelKorSzamlalo(){
        korSzamlalo++;
    }
}


