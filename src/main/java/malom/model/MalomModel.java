package malom.model;

import lombok.Data;
import malom.model.allapot.GepLerak;
import malom.model.allapot.JatekosAllapot;
import malom.model.allapot.JatekosLerak;
import malom.model.jatekos.EmberiJatekos;
import malom.model.jatekos.GepiJatekos;
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
import static malom.model.Pozicio.of;

@Data
public class MalomModel {
    private static final int NUMBER_OF_PLAYERS = 2;

    private JatekElem[][] jatekElemek;
    private Pozicio indulasiPozicio;
    private List<Pozicio> indulasiPozicioSzomszedok;
    private List<JatekVegeListener> jatekVegeListeners;
    private List<ModelValtozottListener> modelValtozottListeners;
    private  List<Jatekos> jatekosok;
    private int jelenlegiJatekosSzam;

    public MalomModel() {
        jelenlegiJatekosSzam = 0;
        indulasiPozicioSzomszedok = new ArrayList<>();
        jatekosok = new ArrayList<>();
        jatekosok.add(new EmberiJatekos(this, new FeherKorong()));
        jatekosok.add(new EmberiJatekos(this, new FeketeKorong()));
        this.jatekElemek = new JatekElem[6][5];

        for (int i = 0; i < jatekElemek.length; i++) {
            for (int j = 0; j < jatekElemek[0].length; j++) {
                jatekElemek[i][j] = new Ures();
            }
        }
        jatekVegeListeners =  new ArrayList<>();
        modelValtozottListeners = new ArrayList<>();
    }

    public void modelValtozott(){  //repaint
        modelValtozottListeners.forEach(ModelValtozottListener::modelValtozott);
    }

    public void jatekVege(){
        jatekVegeListeners.forEach(JatekVegeListener::befejezJatek);
    }

    public void vegrehajt(Pozicio pozicio) {  //A beérkező kattintást ez kezeli le, meghívja a játékos végrehajtó fv-t (Jatekos osztalyban találhato)
        getJatekos().vegrehajt(pozicio);
    }

    public void regisztralJatekVegeListener(JatekVegeListener listener){
        jatekVegeListeners.add(listener);
    }

    public void regisztralModelValtozottListener(ModelValtozottListener listener){
        modelValtozottListeners.add(listener);
    }

    public boolean malomE(Pozicio jelenlegi) {
        String szin = getMezo(jelenlegi).getNev();
        return haromHosszuAzonosSzin(vizszintesSzomszedok(jelenlegi), szin)
                || haromHosszuAzonosSzin(fuggolegesSzomszedok(jelenlegi), szin);
    }

    private boolean haromHosszuAzonosSzin(List<JatekElem> szomszedok, String szin) {
        int szamlalo = 0;
        int max = 0;
        for (JatekElem jatekElem : szomszedok) {
            if (szin.equals(jatekElem.getNev())) {
                szamlalo++;
                if (szamlalo > max) max = szamlalo;
            } else szamlalo = 0;
        }
        return max == 3;
    }

    private List<JatekElem> vizszintesSzomszedok(Pozicio pozicio) {
        List<Pozicio> szomszedok = new ArrayList<>();
        for (int i = pozicio.getOszlop() - 3; i <= pozicio.getOszlop() + 3; i++) {
            szomszedok.add(of(pozicio.getSor(), i));
        }
        return szomszedok.stream()
                .filter(this::letezoPozicoE)  //megnéz, hogy létezik e az a pizíció
                .map(this::getMezo)
                .collect(toList()); //listányi pozícióból listányi mező lett.
    }
//TODO FV ELRENDEZÉS, MIT hova kell rakni
    private List<JatekElem> fuggolegesSzomszedok(Pozicio pozicio) {
        List<Pozicio> szomszedok = new ArrayList<>();
        for (int i = pozicio.getSor() - 3; i <= pozicio.getSor() + 3; i++) {
            szomszedok.add(of(i, pozicio.getOszlop()));
        }
        return szomszedok.stream()
                .filter(this::letezoPozicoE)  //megnéz, hogy létezik e az a pizíció
                .map(this::getMezo)
                .collect(toList());  //listányi pozícióból listányi mező lett.
    }

    public boolean letezoPozicoE(Pozicio pozicio) {
        return pozicio.getSor() >= 0 && pozicio.getSor() < this.jatekElemek.length
                && pozicio.getOszlop() >= 0 && pozicio.getOszlop() < this.jatekElemek[0].length;
    }

    public JatekElem getMezo(Pozicio pozicio) { //lekéri az aktuális pozíziót
        return this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()];
    }

    public void setMezo(Pozicio pozicio, JatekElem elem) { //beállítja az aktuális pozíziót
        this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()] = elem;
    }

    public JatekElem getJatekElem(Pozicio pozicio){
        return this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()];
    }

    public void lehelyezJatekElem(Pozicio pozicio, JatekElem jatekelem) { //adott index párosra beállít egy adott elemet
        this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()] = jatekelem;
    }

    public List<Pozicio> getIndulasiPozicioSzomszedok() {
        return indulasiPozicioSzomszedok;
    }

    public Jatekos getJatekos() {
        return jatekosok.get(jelenlegiJatekosSzam);
    }

    public Jatekos getMasikJatekos() {
        return jatekosok.get((jelenlegiJatekosSzam + 1) % NUMBER_OF_PLAYERS);
    }

    public void novelKorSzam() {
        getJatekos().novelKorSzamlalo();
    }

    public void setJatekosAllapot(JatekosAllapot jatekosAllapot){
        getJatekos().setAllapot(jatekosAllapot);
    }

    public void valtJatekos(){
        jelenlegiJatekosSzam = (jelenlegiJatekosSzam + 1) % NUMBER_OF_PLAYERS;
        getJatekos().autoVegrehajt();
    }

    public JatekElem getJatekosKorong(){
        return getJatekos().getJatekElem();
    }

    public boolean mezoUresE(Pozicio pozicio){
        return getJatekElem(pozicio).uresE();
    }

    public boolean jatekosSzinEgyezikMezonLevoKoronggal(Pozicio pozicio){
        return getJatekElem(pozicio).equals(getJatekosKorong());
    }

    public boolean masikJatekosSzinEgyezikMezonLevoKoronggal(Pozicio pozicio){
        return getJatekElem(pozicio).equals(getMasikJatekos().getJatekElem());
    }
}

