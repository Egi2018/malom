package malom.model.jatekos;

import lombok.Data;
import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.JatekosAllapot;
import malom.model.tabladolgai.JatekElem;

@Data
public abstract class Jatekos {
    protected JatekosAllapot allapot;
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


