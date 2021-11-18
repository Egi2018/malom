package malom.model.tabladolgai;

public class FeherKorong extends JatekElem {
    public FeherKorong() {
        super("feher");
    }

    @Override
    public boolean uresE() { //ez azért kell mert a szülő osztályban nincs beállítva egy adott értékre az uresE fv értéke emiatt neki kell folyton beállítani
        return false;
    }
}
