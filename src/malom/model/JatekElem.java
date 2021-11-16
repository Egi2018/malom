package malom.model;

import java.util.Objects;

public abstract class JatekElem {
    protected String nev;

    public JatekElem(String nev) {
        this.nev = nev;
    }

    public abstract boolean ures();

    public String getNev() {
        return nev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JatekElem jatekElem = (JatekElem) o;
        return Objects.equals(nev, jatekElem.nev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev);
    }
}
