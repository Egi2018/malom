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
        if(levehetE(pozicio))
            palya.lehelyezJatekElem(pozicio, new Ures());
        if(nyert() && palya.getMasikJatekos().getKorSzamlalo() >= MAX_KORSZAM) palya.jatekVege();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if(levehetE(pozicio)) {
            if(palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM){
                this.palya.setJatekosAllapot(new JatekosKijelol(palya));
            } else{
                this.palya.setJatekosAllapot(new JatekosLerak(palya));
            }
            palya.valtJatekos();
        }
    }

    private boolean levehetE(Pozicio pozicio) {
        return palya.malomE(pozicio) && getKorongSzam() == 3 && palya.getMasikJatekos().getKorSzamlalo() >= MAX_KORSZAM
                || !palya.malomE(pozicio);
    }

    public boolean nyert() {
        int korongSzamlalo = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(of(i, j))) korongSzamlalo++;
            }
        }
        return korongSzamlalo < 3;
    }

    private int getKorongSzam(){
        int korongSzam  = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(of(i, j))) korongSzam++;
            }
        }
        return korongSzam;
    }
}
