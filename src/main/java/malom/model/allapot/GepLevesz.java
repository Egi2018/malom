package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class GepLevesz extends JatekosAllapot {
    public GepLevesz(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {

    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        palya.valtJatekos();
    }
}
