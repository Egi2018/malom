package malom.model.allapot;

import malom.model.FeherKorong;
import malom.model.MalomModel;
import malom.model.Pozicio;

public class JatekosLerak extends Allapot{
    private static final int MAX_KORSZAM = 3;

    public JatekosLerak(MalomModel malomModel) {
        super(malomModel);
    }

    @Override
    public void vegrehajt(Pozicio pozicio) {
        this.palya.setJatekElem(pozicio, new FeherKorong());
        if(palya.getKorSzamlalo() == MAX_KORSZAM)
            this.palya.setAllapot(new JatekosKijelol(palya));
    }
}
