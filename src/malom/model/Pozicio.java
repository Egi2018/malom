package malom.model;

import lombok.Data;
@Data
public class Pozicio {
    private final int sor;
    private final int oszlop;

    public Pozicio(int sor, int oszlop) {
        this.sor = sor;
        this.oszlop = oszlop;
    }

    public static Pozicio of(int sor, int oszlop){  //pozició létrehozó
        return new Pozicio(sor, oszlop);
    }
}
