package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;
import malom.view.JatekVegeListener;
import malom.view.ModelValtozottListener;

import java.util.List;

public class GepLevesz extends JatekosAllapot {
    public GepLevesz(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                if (palya.getJatekElem(new Pozicio(i, j)).equals(palya.getMasikJatekos().getJatekElem())) { //TODO MALOME
                    palya.setJatekElem(new Pozicio(i , j), new Ures());
                    palya.getModelValtozottListeners().forEach(ModelValtozottListener::modelValtozott);
                    return;
                }
            }
        }
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        palya.valtJatekos();
    }
}
