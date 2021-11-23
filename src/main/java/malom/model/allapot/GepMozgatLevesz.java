package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;

import java.util.List;

import static malom.model.Pozicio.of;

public class GepMozgatLevesz extends JatekosAllapot {
    public GepMozgatLevesz(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++) {
                Pozicio jelenlegiPozicio = of(i, j);
                List<Pozicio> szomszedok = szomszedosSzabadCellak(jelenlegiPozicio);
                if (palya.jatekosSzinEgyezikMezonLevoKoronggal(jelenlegiPozicio)            // A mi színünk az-e
                        && !szomszedok.isEmpty()) {
                    mozgat(jelenlegiPozicio, szomszedok.get(0));
                    if (palya.malomE(szomszedok.get(0))) {
                        leveszEllenfelKorong();
                        palya.modelValtozott();
                        if (nyert() && palya.getMasikJatekos().getKorSzamlalo() >= MAX_KORSZAM) {
                            palya.jatekVege();
                        }
                    }
                    palya.modelValtozott();
                    return;
                }
            }
        }
        //palya.valtJatekos();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {}

    private void mozgat(Pozicio forras, Pozicio cel) {  //gep
        if (!palya.getMezo(forras).uresE() && palya.getMezo(cel).uresE()) {
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(forras));
            palya.setMezo(forras, seged);
        }
    }
}
