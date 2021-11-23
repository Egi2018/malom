package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

import static malom.model.Pozicio.of;

public class GepLerak extends JatekosAllapot{

    public GepLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                Pozicio jelenlegiPozicio = of(i, j);
                if(palya.mezoUresE(jelenlegiPozicio)) {
                    palya.novelKorSzam();
                    palya.lehelyezJatekElem(jelenlegiPozicio, palya.getJatekosKorong());
                    if(palya.malomE(jelenlegiPozicio))  leveszEllenfelKorong();
                    palya.modelValtozott();
                    return;
                }
            }
        }
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if(palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM) {
            palya.setJatekosAllapot(new GepMozgatLevesz(palya));
        }
        palya.valtJatekos();
    }
}
