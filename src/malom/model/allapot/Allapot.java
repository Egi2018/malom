package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public abstract class Allapot {
    protected MalomModel palya;

    public Allapot(MalomModel palya) {
        this.palya = palya;
    }

    public abstract void vegrehajt(Pozicio pozicio);
}
