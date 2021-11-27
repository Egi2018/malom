package malom.model.tabladolgai;

/**
 * A fekete korongok osztálya.
 */
public class FeketeKorong extends JatekElem {
    public FeketeKorong() {
        super("fekete");
    }

    /**
     * @return Eldönti egy mezőről, hogy üres-e. Ez azért
     */
    @Override
    //ez azért kell mert a szülő osztályban nincs beállítva egy adott értékre az uresE fv értéke emiatt neki kell folyton beállítani
    public boolean uresE() {
        return false;
    }
}
