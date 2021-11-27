package malom.model;

import malom.model.allapot.Allapot;
import malom.model.allapot.JatekosLerak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static malom.model.Pozicio.letrehozUjPozicio;

public class JatekosKijelolTest extends MalomTest{

    private MalomModel palya;
    private Allapot allapot;

    @BeforeEach
    public void setUp(){
        this.palya = new MalomModel("ember");
        this.allapot = new JatekosLerak(palya);
    }

    @Test
    public void testLerak(){
        Pozicio cel = letrehozUjPozicio(1, 2);
        palya.setJatekElmek(olvasPalya("palya_kijelol.txt"));
        allapot.vegrehajt(cel);
        Assertions.assertFalse(palya.mezoUresE(cel));
    }
}
