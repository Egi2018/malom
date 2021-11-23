package malom.model;

import malom.model.tabladolgai.FeherKorong;
import malom.model.tabladolgai.FeketeKorong;
import malom.model.tabladolgai.JatekElem;
import malom.model.tabladolgai.Ures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MalomModelTest {

    private MalomModel palya;

    @BeforeEach
    public void setUp(){
        this.palya = new MalomModel("ember");
    }

    @Test
    public void testPozicioLetezik(){
        boolean letezoE = palya.letezoPozicoE(Pozicio.of(1, 3));
        Assertions.assertTrue(letezoE);
    }

    @Test
    public void testPozicioNemLetezik(){
        boolean letezoE = palya.letezoPozicoE(Pozicio.of(8, 3));
        Assertions.assertFalse(letezoE);
    }

    @Test
    public void testMalom(){
        palya.setJatekElmek(olvasPalya("palya.txt"));
        boolean malomE = palya.malomE(Pozicio.of(1,3));
        Assertions.assertTrue(malomE);
    }

    @Test
    public void testNemMalom(){
        palya.setJatekElmek(olvasPalya("palya.txt"));
        boolean malomE = palya.malomE(Pozicio.of(0,3));
        Assertions.assertFalse(malomE);
    }

    private JatekElem[][] olvasPalya(String fajlNev) {
        JatekElem[][] palyaElemek = new JatekElem[6][5];
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fajlNev);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String sor;
            int i = 0;
            while((sor = br.readLine()) != null){
                String[] elemek = sor.split(" ");
                for (int j = 0; j < elemek.length; j++) {
                    if(Integer.parseInt(elemek[j]) == 0) palyaElemek[i][j] = new Ures();
                    if(Integer.parseInt(elemek[j]) == 1) palyaElemek[i][j] = new FeherKorong();
                    if(Integer.parseInt(elemek[j]) == 2) palyaElemek[i][j] = new FeketeKorong();
                }
                i++;
            }
        }catch (Exception ignored){}
        return palyaElemek;
    }
}
