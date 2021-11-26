package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.Random;

import static malom.model.Pozicio.of;

public class GepLerak extends Allapot {

    private static Random random = new Random();

    public GepLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        Pozicio jelenlegiPozicio = of(random.nextInt(6), random.nextInt(5));
        while (!palya.getMezo(jelenlegiPozicio).uresE()){
            jelenlegiPozicio = of(random.nextInt(6), random.nextInt(5));
        }
        if (palya.mezoUresE(jelenlegiPozicio)) {
            palya.novelKorSzam();
            palya.lehelyezJatekElem(jelenlegiPozicio, palya.getJatekosKorong());
            if (palya.malomE(jelenlegiPozicio)) leveszEllenfelKorong();
            palya.modelValtozott();
            return;
        }
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if (palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM) {
            palya.setJatekosAllapot(new GepMozgatLevesz(palya));
        }
        palya.valtJatekos();
    }
}
