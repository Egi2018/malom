package malom.model;

import malom.model.allapot.Allapot;
import malom.model.tabladolgai.JatekElem;
import malom.model.tabladolgai.Ures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static malom.model.Pozicio.letrehozUjPozicio;

public class JatekosElmozditTest extends MalomTest{

    private MalomModel palya;


    @BeforeEach
    public void setUp(){
        this.palya = new MalomModel("ember");
    }

    @Test
    public void testElmozdit(){
        Pozicio elmozditElem = letrehozUjPozicio(3, 2);
        Pozicio cel = letrehozUjPozicio(4, 2);
        palya.setJatekElmek(olvasPalya("palya_elmozdit.txt"));
        palya.setIndulasiPozicio(elmozditElem);
        mozgat(elmozditElem, cel);
        Assertions.assertTrue(palya.mezoUresE(elmozditElem));
    }

    @Test
    public void testElmozditHibas(){
        Pozicio elmozditElem = letrehozUjPozicio(3, 2);
        Pozicio cel = letrehozUjPozicio(4, 2);
        palya.setJatekElmek(olvasPalya("palya_elmozdit.txt"));
        palya.setIndulasiPozicio(elmozditElem);
        mozgat(elmozditElem, cel);
        Assertions.assertFalse(palya.mezoUresE(cel));
    }

    private void mozgat(Pozicio forras, Pozicio cel) {
        if (!palya.getMezo(forras).uresE() && palya.getMezo(cel).uresE()) {
            JatekElem seged = palya.getMezo(cel);
            palya.setMezo(cel, palya.getMezo(forras));
            palya.setMezo(forras, seged);
        }
    }
}
