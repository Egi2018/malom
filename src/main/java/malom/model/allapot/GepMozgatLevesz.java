package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;
import malom.model.tabladolgai.Ures;
import malom.view.ModelValtozottListener;

import java.util.List;

public class GepMozgatLevesz extends JatekosAllapot {
    public GepMozgatLevesz(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                List<Pozicio> szomszedok = szomszedosSzabadCellak(new Pozicio(i, j));
                if (palya.getJatekElem(new Pozicio(i, j)).equals(palya.getJatekos().getJatekElem())            // A mi színünk az-e
                    && !szomszedok.isEmpty()){
                    mozgat(new Pozicio(i, j), szomszedok.get(0));
                    if(palya.malomE(new Pozicio(i , j)))  levesz();
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

    private void mozgat(Pozicio forras, Pozicio cel){  //gep
        if(!palya.getMezo(forras).ures() && palya.getMezo(cel).ures()){
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(forras));
            palya.setMezo(forras, seged);
        }
    }
}
