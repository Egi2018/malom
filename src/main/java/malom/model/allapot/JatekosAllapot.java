package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;
import malom.view.ModelValtozottListener;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;

public abstract class JatekosAllapot {
    protected static final int MAX_KORSZAM = 6;

    protected MalomModel palya;

    public JatekosAllapot(MalomModel palya) {
        this.palya = palya;
    }

    public boolean szabadE(Pozicio pozicio){
        return true;
    }

    public abstract void vegrehajt(Pozicio pozicio);

    public abstract void setKovetkezoAllapot(Pozicio pozicio);

    protected List<Pozicio> szomszedosSzabadCellak(Pozicio pozicio){
        return szomszedok(pozicio)
                .stream()                    //át alakítjuk
                .filter(this::letezoUresPozicoE)
                .collect(toList());  //vissza alakítjuk
    }

    private List<Pozicio> szomszedok(Pozicio pozicio){ //lehetséges szomszédokat adjuk vissza
        if(pozicio == null)return new ArrayList<>();
        List<Pozicio> szomszedok = new ArrayList<>();
        szomszedok.add(of(pozicio.getSor()-1,pozicio.getOszlop()));
        szomszedok.add(of(pozicio.getSor()+1,pozicio.getOszlop()));
        szomszedok.add(of(pozicio.getSor(),pozicio.getOszlop()-1));
        szomszedok.add(of(pozicio.getSor(),pozicio.getOszlop()+1));
        return szomszedok;
    }

    private boolean letezoUresPozicoE(Pozicio pozicio){
        return palya.letezoPozicoE(pozicio) && palya.getMezo(pozicio).uresE();
    }

    public void leveszEllenfelKorong() {
        int korongSzamlalo = 0;
        for (int k = 0; k < palya.getJatekElemek().length; k++) {
            for (int l = 0; l < palya.getJatekElemek()[0].length; l++){
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(of(k, l))) korongSzamlalo++;
            }
        }
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                Pozicio jelenlegiPozicio = of(i, j);
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(jelenlegiPozicio)) { //TODO MALOME
                    if(palya.malomE(jelenlegiPozicio) && korongSzamlalo <= 3){
                        palya.lehelyezJatekElem(jelenlegiPozicio, new Ures());
                        palya.modelValtozott();
                        return;
                    }
                    palya.lehelyezJatekElem(jelenlegiPozicio, new Ures());
                    palya.modelValtozott();
                    return;
                }
            }
        }
    }
}
