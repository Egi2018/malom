package malom.model;

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

    public int getSor() {
        return sor;
    }

    public int getOszlop() {
        return oszlop;
    }

    @Override
    public String toString() {
        return "Pozicio{" +
                "sor=" + sor +
                ", oszlop=" + oszlop +
                '}';
    }
}
