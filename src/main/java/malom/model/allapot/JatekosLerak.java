package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosLerak extends JatekosAllapot {

    public JatekosLerak(MalomModel palya) {
        super(palya);
    }

    @Override
    public boolean szabadE(Pozicio pozicio) {
        return palya.getMezo(pozicio).uresE();
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        palya.lehelyezJatekElem(pozicio, palya.getJatekosKorong());
        palya.novelKorSzam();
    }

    @Override
    public void setKovetkezoAllapot(Pozicio pozicio) {
        if(palya.malomE(pozicio)){
            palya.setJatekosAllapot(new JatekosLeveszLerakFazisban(palya));
        } else if(palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM){
            palya.setJatekosAllapot(new JatekosKijelol(palya));
            palya.valtJatekos();
        }else {
            palya.valtJatekos();
        }
    }
}
