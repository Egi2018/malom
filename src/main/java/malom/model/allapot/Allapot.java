package malom.model.allapot;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.Ures;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.LetrehozUjPozicio;

/**
 * A játék adott állapotait segít végrehajtani.
 */
public abstract class Allapot {
    protected static final int MAX_KORSZAM = 4;

    protected MalomModel palya;

    /**
     * Allapot osztály kostruktora ami átvesz egy pályát.
     *
     * @param palya egy malom pálya.
     */
    public Allapot(MalomModel palya) {
        this.palya = palya;
    }

    /**
     * Eldönti egy adott pozicióról, hogy szabad pozició-e.
     *
     * @param pozicio ez egy pizicíó a pályán.
     * @return visszaadja, hogy lehetséges-e az adott lépés.
     */
    public boolean szabadE(Pozicio pozicio) {
        return true;
    }

    /**
     * Ez a metódus felel az adott fázisokban a feladatok végre hajtásáért.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    public abstract void vegrehajt(Pozicio pozicio);

    /**
     * Ez a metódus egy pozició alapján beállítja esetleg el is dönti milyen állapotba kell tovább haladnunk.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    public abstract void setKovetkezoAllapot(Pozicio pozicio);

    /**
     * Ez a metódus az adott pozicióra amit megkap megnézi, mely poziciók azok ami szomszédosak és egyidejüleg még szabadak is.
     *
     * @param pozicio egy pályán lévő pozíció.
     * @return visszaajda az üres szomszédos cellákat.
     */
    protected List<Pozicio> szomszedosSzabadCellak(Pozicio pozicio) {
        return szomszedok(pozicio)
                .stream()                    //át alakítjuk
                .filter(this::letezoUresPozicoE)
                .collect(toList());  //vissza alakítjuk
    }

    /**
     * A függvény egy adott pozicióra visszaadja a lehetséges szomszédos mezőket amik ott vannak.
     *
     * @param pozicio egy pályán lévő pozíció.
     * @return visszaadja a szomszédos cellákat.
     */
    private List<Pozicio> szomszedok(Pozicio pozicio) { //lehetséges szomszédokat adjuk vissza
        if (pozicio == null) return new ArrayList<>();
        List<Pozicio> szomszedok = new ArrayList<>();
        szomszedok.add(LetrehozUjPozicio(pozicio.getSor() - 1, pozicio.getOszlop()));
        szomszedok.add(LetrehozUjPozicio(pozicio.getSor() + 1, pozicio.getOszlop()));
        szomszedok.add(LetrehozUjPozicio(pozicio.getSor(), pozicio.getOszlop() - 1));
        szomszedok.add(LetrehozUjPozicio(pozicio.getSor(), pozicio.getOszlop() + 1));
        return szomszedok;
    }

    /**
     * Ez a függvény egy adott pozicióra megnézi, hogy üres-e, illetve, hogy egyátalán létező pozició-e.
     *
     * @param pozicio egy pályán lévő pozíció.
     * @return eldönti, hogy az adott pozició létezik és üres-e.
     */
    private boolean letezoUresPozicoE(Pozicio pozicio) {
        return palya.letezoPozicoE(pozicio) && palya.getMezo(pozicio).uresE();
    }

    /**
     * Ez a függvény felel azért, hogy egy kiválasztott korongot le lehessen venni.
     */
    public void leveszEllenfelKorong() {
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++) {
                Pozicio jelenlegiPozicio = LetrehozUjPozicio(i, j);
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(jelenlegiPozicio)
                        && levehetE(jelenlegiPozicio)) {
                    palya.lehelyezJatekElem(jelenlegiPozicio, new Ures());
                    palya.modelValtozott();
                    return;
                }
            }
        }
    }

    /**
     * Ez a függvény ellenörzi azt, hogy a játék véget ért-e azaz van e nyertes.
     */
    protected boolean nyert() {
        return getKorongSzam() < 3;
    }

    /**
     * Ez a függvény lekéri a koronszámot.
     */
    protected int getKorongSzam() {
        int korongSzam = 0;
        for (int i = 0; i < palya.getJatekElemek().length; i++) {
            for (int j = 0; j < palya.getJatekElemek()[0].length; j++) {
                if (palya.masikJatekosSzinEgyezikMezonLevoKoronggal(LetrehozUjPozicio(i, j))) korongSzam++;
            }
        }
        return korongSzam;
    }

    /**
     * Ez a metódus felel azért, hogy egy kiválasztott korongról eldöntse, hogy le lehet-e venni.
     *
     * @param pozicio egy pályán lévő pozíció.
     * @return eldönti, hogy lelehet-e venni az adott pozíción lévő korongot.
     */
    protected boolean levehetE(Pozicio pozicio) {
        return (palya.malomE(pozicio) && getKorongSzam() == 3
                && palya.getJatekos().getKorSzamlalo() >= MAX_KORSZAM)
                || !palya.malomE(pozicio);
    }
}
