package malom.model;

import malom.model.allapot.Allapot;
import malom.model.allapot.JatekosLerak;
import malom.model.allapot.JatekosLeveszMozgatasFazisban;
import malom.model.tabladolgai.Ures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static malom.model.Pozicio.*;

public class JatekosLeveszMozgatFazisbanTest extends MalomTest{

    private MalomModel palya;
    private Allapot allapot;

    @BeforeEach
    public void setUp(){
        this.palya = new MalomModel("ember");
        this.allapot = new JatekosLeveszMozgatasFazisban(palya);
    }

    @Test
    public void testLeveszMozgatFazisban(){
        Pozicio leveszElem = letrehozUjPozicio(1, 2);
        palya.setJatekElmek(olvasPalya("palya_levesz.txt"));
        allapot.vegrehajt(leveszElem);
        Assertions.assertTrue(palya.mezoUresE(leveszElem));
    }
}
