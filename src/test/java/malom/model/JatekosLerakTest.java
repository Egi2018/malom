package malom.model;

import malom.model.allapot.Allapot;
import malom.model.allapot.JatekosLerak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static malom.model.Pozicio.letrehozUjPozicio;

public class JatekosLerakTest extends MalomTest{

    private MalomModel palya;
    private Allapot allapot;

    @BeforeEach
    public void setUp(){
        this.palya = new MalomModel("ember");
        this.allapot = new JatekosLerak(palya);
    }

    @Test
    public void testLerak(){
        Pozicio lehelyezesPozicio = letrehozUjPozicio(1, 2);
        palya.setJatekElmek(olvasPalya("palya_ures.txt"));
        allapot.vegrehajt(lehelyezesPozicio);
        Assertions.assertFalse(palya.mezoUresE(lehelyezesPozicio));
    }

    @Test
    public void testUresE(){
        Pozicio lehelyezesPozicio = letrehozUjPozicio(1, 3);
        palya.setJatekElmek(olvasPalya("palya_ures.txt"));
        allapot.szabadE(lehelyezesPozicio);
        Assertions.assertTrue(palya.mezoUresE(lehelyezesPozicio));
    }
}