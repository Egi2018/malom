package malom.model.tabladolgai;

/**
 * A fehér korongok osztálya.
 */
public class FeherKorong extends JatekElem {
    public FeherKorong() {
        super("feher");
    }

    /**
     * @return Eldönti egy mezőről, hogy üres-e.
     */
    @Override
    public boolean uresE() { //ez azért kell mert a szülő osztályban nincs beállítva egy adott értékre az uresE fv értéke emiatt neki kell folyton beállítani
        return false;
    }
}
