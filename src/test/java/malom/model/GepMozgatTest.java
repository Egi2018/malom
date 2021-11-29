package malom.model;

import malom.model.allapot.Allapot;
import malom.model.allapot.GepLerak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static malom.model.Pozicio.letrehozUjPozicio;

public class GepMozgatTest extends MalomTest{

    private MalomModel palya;
    private Allapot allapot;

    @BeforeEach
    public void setUp(){
        this.palya = new MalomModel("gep");
        this.allapot = new GepLerak(palya);
    }

    @Test
    public void testLerak(){
        Pozicio elmozdit = letrehozUjPozicio(1, 3);
        palya.setJatekElmek(olvasPalya("palya_gep_mozgat.txt"));
        allapot.vegrehajt(elmozdit);
        Assertions.assertTrue(palya.mezoUresE(elmozdit));
    }
}
