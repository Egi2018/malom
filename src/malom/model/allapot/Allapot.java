package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public abstract class Allapot {
    protected MalomModel palya;
    protected int jatekosSzam;

    public Allapot(MalomModel palya, int jatekosSzam) {
        this.palya = palya;
        this.jatekosSzam = jatekosSzam;
    }

    public abstract void vegrehajt(Pozicio pozicio);
}
