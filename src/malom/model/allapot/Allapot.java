package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;
/** A játék adott állapotait segít végrehajtani. */
public abstract class Allapot {
    protected static final int MAX_KORSZAM = 12;

    protected MalomModel palya;
    protected int jatekosSzam;
/** Az Allapot osztály konstruktora ami a pályát és a játékos számot állítja be. */
    public Allapot(MalomModel palya, int jatekosSzam) {
        this.palya = palya;
        this.jatekosSzam = jatekosSzam;
    }
/** Eldönti egy adott pozicióról, hogy szabad pozició-e. */
    public boolean szabadE(Pozicio pozicio){
        return true;
    }
/** Ez a metódus felel az adott fázisokban a feladatok végre hajtásáért. */
    public abstract void vegrehajt(Pozicio pozicio);
/** Ez a metódus az adott pozicióra amit megkap megnézi, mely poziciók azok ami szomszédosak és egyidejüleg még szabadak is. */
    protected List<Pozicio> szomszedosSzabadCellak(Pozicio pozicio){
        return szomszedok(pozicio)
                .stream()                    //át alakítjuk
                .filter(this::letezoUresPozicoE)
                .collect(toList());  //vissza alakítjuk
    }
/** A függvény egy adott pozicióra visszaadja a lehetséges szomszédos mezőket amik ott vannak. */
    private List<Pozicio> szomszedok(Pozicio pozicio){ //lehetséges szomszédokat adjuk vissza
        if(pozicio == null)return new ArrayList<>();
        List<Pozicio> szomszedok = new ArrayList<>();
        szomszedok.add(of(pozicio.getSor()-1,pozicio.getOszlop()));
        szomszedok.add(of(pozicio.getSor()+1,pozicio.getOszlop()));
        szomszedok.add(of(pozicio.getSor(),pozicio.getOszlop()-1));
        szomszedok.add(of(pozicio.getSor(),pozicio.getOszlop()+1));
        return szomszedok;
    }
/** Ez a függvény egy adott pozicióra megnézi, hogy üres-e, illetve, hogy egyátalán létező pozició-e. */
    private boolean letezoUresPozicoE(Pozicio pozicio){
        return palya.letezoPozicoE(pozicio) && palya.getMezo(pozicio).ures();
    }
}
