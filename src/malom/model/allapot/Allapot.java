package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;

public abstract class Allapot {
    protected static final int MAX_KORSZAM = 7;

    protected MalomModel palya;
    protected int jatekosSzam;

    public Allapot(MalomModel palya, int jatekosSzam) {
        this.palya = palya;
        this.jatekosSzam = jatekosSzam;
    }

    public boolean szabadE(Pozicio pozicio){
        return true;
    }

    public abstract void vegrehajt(Pozicio pozicio);

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
        return palya.letezoPozicoE(pozicio) && palya.getMezo(pozicio).ures();
    }
}
