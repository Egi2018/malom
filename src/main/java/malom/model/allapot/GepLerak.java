package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.Random;

import static malom.model.Pozicio.letrehozUjPozicio;

/**
 * Ez az osztály végzi el a gép elleni játékban az első játékrészben (a korongok lerakásánál) a gép feladatait.
 */
public class GepLerak extends Allapot {

    private static Random random = new Random();

    /**
     * GepLerak osztály konstruktora.
     *
     * @param palya a malom játék pályája.
     */
    public GepLerak(MalomModel palya) {
        super(palya);
    }

    /**
     * Ez a metódus a gépi játékos korongjainak lerakásáért felel, paraméterként egy adott pozíciót kap meg ez alapján végzi el a feladatát.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        Pozicio jelenlegiPozicio = letrehozUjPozicio(random.nextInt(6), random.nextInt(5));
        while (!palya.getMezo(jelenlegiPozicio).uresE()) {
            jelenlegiPozicio = letrehozUjPozicio(random.nextInt(6), random.nextInt(5));
        }
        if (palya.mezoUresE(jelenlegiPozicio)) {
            palya.novelKorSzam();
            palya.lehelyezJatekElem(jelenlegiPozicio, palya.getJatekosKorong());
            if (palya.malomE(jelenlegiPozicio)) leveszEllenfelKorong();
            palya.modelValtozott();
            return;
        }
    }

    /**
     * Ez a metódus egy pozíciót vesz át paraméterül és endönti, hogy melyik állapotba kell tovább küldeni a gépi játékost.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM) {
            palya.setJatekosAllapot(new GepMozgatLevesz(palya));
        }
        palya.valtJatekos();
    }
}
