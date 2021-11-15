package malom.model;

public abstract class JatekElem {
    protected String nev;

    public JatekElem(String nev) {
        this.nev = nev;
    }

    public abstract boolean ures();

    public String getNev() {
        return nev;
    }
}
