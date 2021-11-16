package malom.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pozicio pozicio = (Pozicio) o;
        return sor == pozicio.sor && oszlop == pozicio.oszlop;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sor, oszlop);
    }

    @Override
    public String toString() {
        return "Pozicio{" +
                "sor=" + sor +
                ", oszlop=" + oszlop +
                '}';
    }
}
