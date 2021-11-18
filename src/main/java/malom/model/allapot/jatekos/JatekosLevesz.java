package malom.model.allapot.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.Ures;
import malom.view.JatekVegeListener;

public class JatekosLevesz extends JatekosAllapot {

    public JatekosLevesz(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return !palya.getMezo(pozicio).equals(palya.getJatekos().getJatekElem())
                && !palya.getMezo(pozicio).ures();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        palya.setJatekElem(pozicio, new Ures());
        if(nyert()) palya.getListeners().forEach(JatekVegeListener::befejezJatek);
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

    public boolean nyert() {
        int korongSzamlalo = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                if (palya.getJatekElem(new Pozicio(i, j)).equals(palya.getMasikJatekos().getJatekElem())) korongSzamlalo++;
            }
        }
        return korongSzamlalo < 3;
    }
}
