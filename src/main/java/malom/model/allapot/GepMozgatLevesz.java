package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;

import java.util.List;

import static malom.model.Pozicio.LetrehozUjPozicio;

/**
 * A GepMozgatLevesz osztály végzi a gép mozgatással és levétellel kapcsolatos metódusait.
 */
public class GepMozgatLevesz extends Allapot {
    /**
     * GepMozgatLevesz osztály konstruktora.
     *
     * @param palya a malom játék pályája.
     */
    public GepMozgatLevesz(MalomModel palya) {
        super(palya);
    }

    /**
     * Ez a metódus a korongok mozgatásáért és az esetleges levételéért felel. Paraméterül egy poziciót vesz át.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++) {
                Pozicio jelenlegiPozicio = LetrehozUjPozicio(i, j);
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
    }

    /**
     * Ez a metódus egy pozíciót vesz át paraméterül és beállítja a következő állapotot, ami ebben az esetben a játékos váltás.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        palya.valtJatekos();
    }

    /**
     * Ez a függvény végzi egy adott korong elmozdítását, adott pozícióra.
     *
     * @param forras indulási pozíció.
     * @param cel    érkezés pizíció.
     */
    private void mozgat(Pozicio forras, Pozicio cel) {  //gep
        if (!palya.getMezo(forras).uresE() && palya.getMezo(cel).uresE()) {
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(forras));
            palya.setMezo(forras, seged);
        }
    }
}
