package malom.model;

import lombok.Data;
import malom.model.allapot.Allapot;
import malom.model.jatekos.EmberiJatekos;
import malom.model.jatekos.Jatekos;
import malom.model.tabladolgai.FeherKorong;
import malom.model.tabladolgai.FeketeKorong;
import malom.model.tabladolgai.JatekElem;
import malom.model.tabladolgai.Ures;
import malom.view.JatekVegeListener;
import malom.view.ModelValtozottListener;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.letrehozUjPozicio;
import static malom.model.jatekos.JatekosFactory.letrehozJatekos;

/**
 * A malom játék pályának műveleteiért felelős, illetve működéséért.
 */
@Data
public class MalomModel {
    private static final int NUMBER_OF_PLAYERS = 2;

    private JatekElem[][] jatekElemek;
    private Pozicio indulasiPozicio;
    private List<Pozicio> indulasiPozicioSzomszedok;
    private List<JatekVegeListener> jatekVegeListeners;
    private List<ModelValtozottListener> modelValtozottListeners;
    private List<Jatekos> jatekosok;
    private int jelenlegiJatekosSzam;

    /**
     * Az osztály konstruktora.
     *
     * @param ellenfelTipus Ez az amit a játék elindításánál kiválasztunk. ("gep", "ember")
     */
    public MalomModel(String ellenfelTipus) {
        jelenlegiJatekosSzam = 0;
        indulasiPozicioSzomszedok = new ArrayList<>();
        jatekosok = new ArrayList<>();
        jatekosok.add(new EmberiJatekos(this, new FeherKorong()));
        jatekosok.add(letrehozJatekos(ellenfelTipus, this, new FeketeKorong()));
        this.jatekElemek = new JatekElem[6][5];

        for (int i = 0; i < jatekElemek.length; i++) {
            for (int j = 0; j < jatekElemek[0].length; j++) {
                jatekElemek[i][j] = new Ures();
            }
        }
        jatekVegeListeners = new ArrayList<>();
        modelValtozottListeners = new ArrayList<>();
    }

    /**
     * Ez a metódus az amit akkor kell meghívni, ha a pálya változott, és ezért újra kikell rajzolni a pályát.
     */
    public void modelValtozott() {  //repaint
        modelValtozottListeners.forEach(ModelValtozottListener::modelValtozott);
    }

    /**
     * Ez a metódus azért felel, hogy a játék bezáródjon.
     */
    public void jatekVege() {
        jatekVegeListeners.forEach(JatekVegeListener::befejezJatek);
    }

    /**
     * A beérkező kattintást ez kezeli le, meghívja a játékos végrehajtó fv-t (Jatekos osztalyban találhato)
     *
     * @param pozicio egy pályán lévő pizíció.
     */
    public void vegrehajt(Pozicio pozicio) {
        getJatekos().vegrehajt(pozicio);
    }

    /**
     * Létrehozz egy JátékVégeListenert.
     *
     * @param listener Egy JátékVégeListener.
     */
    public void regisztralJatekVegeListener(JatekVegeListener listener) {
        jatekVegeListeners.add(listener);
    }

    /**
     * Létrehoz egy ModelValtozottListenert.
     *
     * @param listener Egy ModelValtozottListener.
     */
    public void regisztralModelValtozottListener(ModelValtozottListener listener) {
        modelValtozottListeners.add(listener);
    }

    /**
     * Ez a metódus fele azért, hogy eldöntse egy adott pozícióról, hogy amlmot alkot-e.
     *
     * @param jelenlegi ez az adott pozíció, amire vizsgáljuk.
     * @return eldönti, hogy malom-e.
     */
    public boolean malomE(Pozicio jelenlegi) {
        JatekElem korong = getMezo(jelenlegi);
        return haromHosszuAzonosSzin(vizszintesSzomszedok(jelenlegi), korong)
                || haromHosszuAzonosSzin(fuggolegesSzomszedok(jelenlegi), korong);
    }

    /**
     * @param szomszedok egy adott pozícióra a szomszédos elemek.
     * @param korong     egy korong a pályán.
     * @return Eldönti, hogy van-e 3 azonos szín azokból az adatokból amiket megkapott.
     */
    private boolean haromHosszuAzonosSzin(List<JatekElem> szomszedok, JatekElem korong) {
        int szamlalo = 0;
        int max = 0;
        JatekElem ures = new Ures();
        for (JatekElem jatekElem : szomszedok) {
            if (korong.equals(jatekElem) && !korong.equals(ures)) {
                szamlalo++;
                if (szamlalo > max) max = szamlalo;
            } else szamlalo = 0;
        }
        return max == 3;
    }

    /**
     * A vízszintes szomszédos elemeket egy megkapott pozíció alapján összegyűjti.
     *
     * @param pozicio Ez egy pozicíó a pályán.
     * @return A szomszédos elemeket egy megkapott pozícióra vízszintes irányban.
     */
    private List<JatekElem> vizszintesSzomszedok(Pozicio pozicio) {
        List<Pozicio> szomszedok = new ArrayList<>();
        for (int i = pozicio.getOszlop() - 3; i <= pozicio.getOszlop() + 3; i++) {
            szomszedok.add(letrehozUjPozicio(pozicio.getSor(), i));
        }
        return szomszedok.stream()
                .filter(this::letezoPozicoE)  //megnéz, hogy létezik e az a pizíció
                .map(this::getMezo)
                .collect(toList()); //listányi pozícióból listányi mező lett.
    }

    /**
     * A függőleges szomszédos elemeket egy megkapott pozíció alapján összegyűjti.
     *
     * @param pozicio Ez egy pozicíó a pályán.
     * @return A szomszédos elemeket egy megkapott pozícióra függőleges irányban.
     */
    private List<JatekElem> fuggolegesSzomszedok(Pozicio pozicio) {
        List<Pozicio> szomszedok = new ArrayList<>();
        for (int i = pozicio.getSor() - 3; i <= pozicio.getSor() + 3; i++) {
            szomszedok.add(letrehozUjPozicio(i, pozicio.getOszlop()));
        }
        return szomszedok.stream()
                .filter(this::letezoPozicoE)  //megnéz, hogy létezik e az a pizíció
                .map(this::getMezo)
                .collect(toList());  //listányi pozícióból listányi mező lett.
    }

    /**
     * @param pozicio A pályán egy pozíció.
     * @return Eldönti, hogy az adott pozíció létezik-e.
     */
    public boolean letezoPozicoE(Pozicio pozicio) {
        return pozicio.getSor() >= 0 && pozicio.getSor() < this.jatekElemek.length
                && pozicio.getOszlop() >= 0 && pozicio.getOszlop() < this.jatekElemek[0].length;
    }

    /**
     * Lekéri az aktuális pozíciót.
     *
     * @param pozicio A pályán egy pozíció.
     * @return Visszaadja az adott pozíción található játék elemet.
     */
    public JatekElem getMezo(Pozicio pozicio) { //lekéri az aktuális pozíziót
        return this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()];
    }

    /**
     * Beállítja az aktuális pozíciót.
     *
     * @param pozicio A pályán egy pozíció.
     * @param elem    Egy játék elem.
     */
    public void setMezo(Pozicio pozicio, JatekElem elem) { //beállítja az aktuális pozíziót
        this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()] = elem;
    }

    /**
     * Lekér egy adott mezőn lévő játék elemet.
     *
     * @param pozicio A pályán egy pozíció.
     * @return Visszaadja, hogy mi található az adott pozíción.
     */
    public JatekElem getJatekElem(Pozicio pozicio) {
        return this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()];
    }

    /**
     * Egy megadott játék elemet lehelyez egy megadott pozícióra.
     *
     * @param pozicio   A pályán egy pozíció.
     * @param jatekelem Egy játék elem.
     */
    public void lehelyezJatekElem(Pozicio pozicio, JatekElem jatekelem) { //adott index párosra beállít egy adott elemet
        this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()] = jatekelem;
    }

    /**
     * Lekéri a lehetséges szomszédos irányokat.
     *
     * @return vissza adja az adott pozíción a szomszédos irányokat.
     */
    public List<Pozicio> getIndulasiPozicioSzomszedok() {
        return indulasiPozicioSzomszedok;
    }

    /**
     * Lekéri a jelenlegi játékost.
     *
     * @return Visszaadja a jelenleg soron lévő játékost.
     */
    public Jatekos getJatekos() {
        return jatekosok.get(jelenlegiJatekosSzam);
    }

    /**
     * Lekéri a nem jelenlegi játékost.
     *
     * @return Visszaadja a nem jelenleg soron lévő játékost.
     */
    public Jatekos getMasikJatekos() {
        return jatekosok.get((jelenlegiJatekosSzam + 1) % NUMBER_OF_PLAYERS);
    }

    /**
     * Növeli az adott játékos körszámlálóját.
     */
    public void novelKorSzam() {
        getJatekos().novelKorSzamlalo();
    }

    /**
     * Beállítja a jelenlegi játékos állapotát.
     *
     * @param allapot Ez az az állapot amiben épp tartózkodik a játékos.
     */
    public void setJatekosAllapot(Allapot allapot) {
        getJatekos().setAllapot(allapot);
    }

    /**
     * Ez a metódus végzi el a játékosok váltását.
     */
    public void valtJatekos() {
        jelenlegiJatekosSzam = (jelenlegiJatekosSzam + 1) % NUMBER_OF_PLAYERS;
        getJatekos().autoVegrehajt();
    }

    /**
     * Lekéri az adott játékos korongját.
     *
     * @return Visszaadja az adott játékos korongját.
     */
    public JatekElem getJatekosKorong() {
        return getJatekos().getJatekElem();
    }

    /**
     * @param pozicio A pályán egy pozíció.
     * @return Eldönti, hogy az adott mező üres-e.
     */
    public boolean mezoUresE(Pozicio pozicio) {
        return getJatekElem(pozicio).uresE();
    }

    /**
     * @param pozicio A pályán egy pozíció.
     * @return Eldönti, hogy az adott pozíción lévő korong egyezik-e az épp soron lévő játékos korongjával.
     */
    public boolean jatekosSzinEgyezikMezonLevoKoronggal(Pozicio pozicio) {
        return getJatekElem(pozicio).equals(getJatekosKorong());
    }

    /**
     * @param pozicio A pályán egy pozíció.
     * @return Eldönti, hogy az adott pozíción lévő korong egyezik-e az épp nem soron lévő játékos korongjával.
     */
    public boolean masikJatekosSzinEgyezikMezonLevoKoronggal(Pozicio pozicio) {
        return getJatekElem(pozicio).equals(getMasikJatekos().getJatekElem());
    }

    /**
     * Lehelyez játék elemeket a pályára. (Teszteléshez kell.)
     *
     * @param elemek Egy játék elem.
     */
    public void setJatekElmek(JatekElem[][] elemek) {
        for (int i = 0; i < jatekElemek.length; i++) {
            for (int j = 0; j < jatekElemek[0].length; j++) {
                jatekElemek[i][j] = elemek[i][j];
            }
        }
    }
}

