package malom.model;

import malom.model.tabladolgai.FeherKorong;
import malom.model.tabladolgai.FeketeKorong;
import malom.model.tabladolgai.JatekElem;
import malom.model.tabladolgai.Ures;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MalomTest {

    protected JatekElem[][] olvasPalya(String fajlNev) {
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
