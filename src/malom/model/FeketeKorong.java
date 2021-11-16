package malom.model;

public class FeketeKorong extends JatekElem{
    public FeketeKorong() {
        super("fekete");
    }

    @Override  //ez azért kell mert a szülő osztályban nincs beállítva egy adott értékre az uresE fv értéke emiatt neki kell folyton beállítani
    public boolean ures() {
        return false;
    }
}
