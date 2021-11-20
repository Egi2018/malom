package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;

import static malom.model.Pozicio.of;

public class JatekosLevesz extends JatekosAllapot {

    public JatekosLevesz(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return !palya.jatekosSzinEgyezikMezonLevoKoronggal(pozicio)
                && !palya.getMezo(pozicio).uresE();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        if(palya.getMasikJatekos().getKorSzamlalo() >= MAX_KORSZAM && palya.malomE(pozicio) && getKorongSzam() == 3)  //TODO lehet ide szálkezelés kéne, mivel itt ameddig nem lesz egy mező üres nem szabad, hogy tovább menjen
            palya.lehelyezJatekElem(pozicio, new Ures());
        else if(palya.getMasikJatekos().getKorSzamlalo() >= MAX_KORSZAM && !palya.malomE(pozicio))
            palya.lehelyezJatekElem(pozicio, new Ures());
        else if(palya.getMasikJatekos().getKorSzamlalo() <= MAX_KORSZAM && palya.malomE(pozicio) && getKorongSzam() <= 3)
            palya.lehelyezJatekElem(pozicio, new Ures());
        else if(palya.getMasikJatekos().getKorSzamlalo() <= MAX_KORSZAM && !palya.malomE(pozicio))
            palya.lehelyezJatekElem(pozicio, new Ures());
        if(palya.nyert() && palya.getMasikJatekos().getKorSzamlalo() >= MAX_KORSZAM) palya.jatekVege();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if(palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM){
            this.palya.setJatekosAllapot(new JatekosKijelol(palya));
        } else{
            this.palya.setJatekosAllapot(new JatekosLerak(palya));
        }
        palya.valtJatekos();
    }

    public int getKorongSzam(){
        int korongSzamlalo = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++) {
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(of(i, j))) korongSzamlalo++;
            }
        }
        return korongSzamlalo;
    }
}
