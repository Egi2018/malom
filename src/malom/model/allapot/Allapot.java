package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public abstract class Allapot {
    protected static final int MAX_KORSZAM = 2*2;

    protected MalomModel palya;
    protected int jatekosSzam;

    public Allapot(MalomModel palya, int jatekosSzam) {
        this.palya = palya;
        this.jatekosSzam = jatekosSzam;
    }

    public boolean szabadE(Pozicio pozicio){
        return true;
    }

    public abstract void vegrehajt(Pozicio pozicio);
}
