package malom.model;

import lombok.Data;
import malom.model.allapot.Allapot;
import malom.model.allapot.JatekosLerak;
import malom.view.JatekVegeListener;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;

@Data
public class MalomModel {
    private JatekElem[][] jatekElemek;
    private Allapot allapot;
    private Pozicio indulasiPozicio;
    private int korSzamlalo = 0;
    private List<Pozicio> indulasiPozicioSzomszedok;
    private List<JatekElem> jatekosok;
    private List<JatekVegeListener> listeners;

    public MalomModel() {
        allapot = new JatekosLerak(this, 0);
        indulasiPozicioSzomszedok = new ArrayList<>();
        jatekosok = new ArrayList<>();
        jatekosok.add(new FeherKorong());
        jatekosok.add(new FeketeKorong());
        this.jatekElemek = new JatekElem[6][5];

        for (int i = 0; i < jatekElemek.length; i++) {
            for (int j = 0; j < jatekElemek[0].length; j++) {
                jatekElemek[i][j] = new Ures();
            }
        }
        listeners =  new ArrayList<>();
    }

    public void regisztralListener(JatekVegeListener listener){
        listeners.add(listener);
    }

    public void vegrehajt(Pozicio pozicio) {  //TODO beallitAllapot
        if (allapot.szabadE(pozicio)){
            allapot.vegrehajt(pozicio);
            allapot.setKovetkezoAllapot(pozicio);
        }
    }

    public boolean malomE(Pozicio jelenlegi) {
        String szin = getMezo(jelenlegi).nev;
        return haromHosszuAzonosSzin(vizszintesSzomszedok(jelenlegi), szin)
                || haromHosszuAzonosSzin(fuggolegesSzomszedok(jelenlegi), szin);
    }

    private boolean haromHosszuAzonosSzin(List<JatekElem> szomszedok, String szin) {
        int szamlalo = 0;
        int max = 0;
        for (JatekElem jatekElem : szomszedok) {
            if (szin.equals(jatekElem.nev)) {
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

    public void setJatekElem(Pozicio pozicio, JatekElem jatekelem) { //adott index párosra beállít egy adott elemet
        this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()] = jatekelem;
    }

    public List<Pozicio> getIndulasiPozicioSzomszedok() {
        return indulasiPozicioSzomszedok;
    }

    public JatekElem getJatekos(int i) {
        return jatekosok.get(i);
    }

    public void novelKorSzam() {
        korSzamlalo++;
    }

}

