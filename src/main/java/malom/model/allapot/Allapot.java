package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;
import malom.view.ModelValtozottListener;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;

public abstract class Allapot {
    protected static final int MAX_KORSZAM = 4;

    protected MalomModel palya;

    public Allapot(MalomModel palya) {
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
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++){
                Pozicio jelenlegiPozicio = of(i, j);
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(jelenlegiPozicio)
                        && levehetE(jelenlegiPozicio)) { //TODO MALOME
                    palya.lehelyezJatekElem(jelenlegiPozicio, new Ures());
                    palya.modelValtozott();
                    return;
                }
            }
        }
    }

    protected boolean nyert() {
        return getKorongSzam() < 3;
    }

    protected int getKorongSzam() {
        int korongSzam = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++) {
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(of(i, j))) korongSzam++;
            }
        }
        return korongSzam;
    }

    protected boolean levehetE(Pozicio pozicio) {
        return (palya.malomE(pozicio) && getKorongSzam() == 3
                && palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM)
                || !palya.malomE(pozicio);
    }
}
