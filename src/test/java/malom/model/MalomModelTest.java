package malom.model;

import malom.model.tabladolgai.FeherKorong;
import malom.model.tabladolgai.JatekElem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MalomModelTest extends MalomTest {

    private MalomModel palya;

    @BeforeEach
    public void setUp() {
        this.palya = new MalomModel("ember");
    }

    @Test
    public void testPozicioLetezik() {
        boolean letezoE = palya.letezoPozicoE(Pozicio.letrehozUjPozicio(1, 3));
        Assertions.assertTrue(letezoE);
    }

    @Test
    public void testPozicioNemLetezik() {
        boolean letezoE = palya.letezoPozicoE(Pozicio.letrehozUjPozicio(8, 3));
        Assertions.assertFalse(letezoE);
    }

    @Test
    public void testMalom() {
        palya.setJatekElmek(olvasPalya("palya_malom.txt"));
        boolean malomE = palya.malomE(Pozicio.letrehozUjPozicio(1, 3));
        Assertions.assertTrue(malomE);
    }

    @Test
    public void testNemMalom() {
        palya.setJatekElmek(olvasPalya("palya_malom.txt"));
        boolean malomE = palya.malomE(Pozicio.letrehozUjPozicio(0, 3));
        Assertions.assertFalse(malomE);
    }

    @Test
    public void testNemMalom4EgymasMellett() {
        palya.setJatekElmek(olvasPalya("palya_4_egymas_mellett.txt"));
        boolean malomE = palya.malomE(Pozicio.letrehozUjPozicio(2, 3));
        Assertions.assertFalse(malomE);
    }

    @Test
    public void testMezoUres(){
        palya.setJatekElmek(olvasPalya("palya_ures.txt"));
        boolean mezoUresE = palya.mezoUresE(Pozicio.letrehozUjPozicio(1,1));
        Assertions.assertTrue(mezoUresE);
    }

    @Test
    public void testMezoNemUres(){
        palya.setJatekElmek(olvasPalya("palya_ures.txt"));
        boolean mezoUresE = palya.mezoUresE(Pozicio.letrehozUjPozicio(1,2));
        Assertions.assertFalse(mezoUresE);
    }
}
