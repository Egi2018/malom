package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.view.ModelValtozottListener;

public class GepLerak extends JatekosAllapot{

    public GepLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                if(palya.getJatekElem(new Pozicio(i, j)).ures()) {
                    this.palya.setJatekElem(new Pozicio(i, j), palya.getJatekos().getJatekElem());
                    palya.novelKorSzam();
                    if(palya.malomE(new Pozicio(i , j)))  levesz();
                    palya.getModelValtozottListeners().forEach(ModelValtozottListener::modelValtozott);
                    return;
                }
            }
        }
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if(palya.getJatekos().getKorSzamlalo() == MAX_KORSZAM)
            palya.setJatekosAllapot(new GepMozgatLevesz(palya));
        palya.valtJatekos();
    }
}
