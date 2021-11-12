package malom;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import static java.util.stream.Collectors.toList;
import static malom.Pozicio.of;

public class Palya {
    private JatekElem[][] jatekElemek;

    public Palya() {
        this.jatekElemek = new JatekElem[5][6];

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){
                jatekElemek[i][j] = new Ures();
            }
        }
        //jatekElemek[2][2] = new FeherKorong();
        jatekElemek[2][3] = new FeherKorong();
        jatekElemek[2][4] = new FeherKorong();
    }

    public void print(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(jatekElemek[i][j].nev+ " ");
            }
            System.out.println();
        }
    }

    public void mozgat(Pozicio jelenlegi, Pozicio cel){
        if(!getMezo(jelenlegi).ures() && getMezo(cel).ures()){
            JatekElem seged = getMezo(cel);
            setMezo(cel, getMezo(jelenlegi));
            setMezo(jelenlegi, seged);
        }
    }

    //TODO nyerés eldöntése (visszatérési érték, ki nyert (fekete/feher))
    public void nyertes(Palya palya){
        int feherSzamlalo = 0;
        int feketeSzamlalo = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){
                if(!palya.jatekElemek[i][j].nev.equals("ures")){
                    if(palya.jatekElemek[i][j].nev.equals("feher")){
                        feherSzamlalo++;
                    }
                    if(palya.jatekElemek[i][j].nev.equals("fekete")){
                        feketeSzamlalo++;
                    }
                }
            }
        }
        if(feherSzamlalo < 3 && feketeSzamlalo >= 3 ) {
            System.out.println("A fekete nyert");
            System.exit(1);
        }
        if(feketeSzamlalo < 3 && feherSzamlalo >= 3){
            System.out.println("A fehér nyert");
            System.exit(1);
        }
    }

    //TODO automatizált lépések

    /////////////////////////////////////////////////////////////////////////////////////////////
    //TODO PÁLYA KIRAJZOLÁSA


///////////////////////////////////////////////////////////////////////////////////////////////
    public boolean malomE(Pozicio jelenlegi){
        String szin = getMezo(jelenlegi).nev;
       return haromHosszuAzonosSzin(vizszintesSzomszedok(jelenlegi), szin)
        || haromHosszuAzonosSzin(fuggolegesSzomszedok(jelenlegi), szin);
    }

    public List<Pozicio> szomszedosSzabadCellak(Pozicio pozicio){
       return szomszedok(pozicio)
                .stream()                    //át alakítjuk
                .filter(this::letezoUresPozicoE)
                .collect(toList());  //vissza alakítjuk
    }

    private boolean haromHosszuAzonosSzin(List<JatekElem> szomszedok, String szin){
        int szamlalo = 0;
        int max = 0;
        for(JatekElem jatekElem:szomszedok){
            if(szin.equals(jatekElem.nev)){
                szamlalo++;
                if(szamlalo > max)max = szamlalo;
            }
            else szamlalo = 0;
        }
        return max == 3;
    }

    private List<JatekElem> vizszintesSzomszedok(Pozicio pozicio){
        List<Pozicio> szomszedok = new ArrayList<>();
        for(int i = pozicio.getOszlop()-3; i <= pozicio.getOszlop()+3; i++){
            szomszedok.add(of(pozicio.getSor(),i));
        }
        return szomszedok.stream()
                .filter(this::letezoPozicoE)  //megnéz, hogy létezik e az a pizíció
                .map(this::getMezo)
                .collect(toList()); //listányi pozícióból listányi mező lett.
    }

    private List<JatekElem> fuggolegesSzomszedok(Pozicio pozicio){
        List<Pozicio> szomszedok = new ArrayList<>();
        for(int i = pozicio.getSor()-3; i <= pozicio.getSor()+3; i++){
            szomszedok.add(of(i,pozicio.getOszlop()));
        }
        return szomszedok.stream()
                .filter(this::letezoPozicoE)  //megnéz, hogy létezik e az a pizíció
                .map(this::getMezo)
                .collect(toList());  //listányi pozícióból listányi mező lett.
    }

    private List<Pozicio> szomszedok(Pozicio pozicio){ //lehetséges szomszédokat adjuk vissza
        List<Pozicio> szomszedok = new ArrayList<>();
        szomszedok.add(of(pozicio.getSor()-1,pozicio.getOszlop()));
        szomszedok.add(of(pozicio.getSor()+1,pozicio.getOszlop()));
        szomszedok.add(of(pozicio.getSor(),pozicio.getOszlop()-1));
        szomszedok.add(of(pozicio.getSor(),pozicio.getOszlop()+1));
        return szomszedok;
    }

    private boolean letezoPozicoE(Pozicio pozicio){
        return pozicio.getSor() >= 0 && pozicio.getSor() < this.jatekElemek.length
                && pozicio.getOszlop() >= 0 &&pozicio.getOszlop() < this.jatekElemek[0].length;
    }

    private boolean letezoUresPozicoE(Pozicio pozicio){
        return letezoPozicoE(pozicio) && getMezo(pozicio).ures();
    }

    private JatekElem getMezo(Pozicio pozicio){ //lekéri az aktuális pozíziót
        return this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()];
    }

    private void setMezo(Pozicio pozicio, JatekElem elem){ //beállítja az aktuális pozíziót
        this.jatekElemek[pozicio.getSor()][pozicio.getOszlop()] = elem;
    }

    public JatekElem[][] getJatekElemek() {
        return jatekElemek;
    }

    public void setJatekElem(int sor, int oszlop, JatekElem jatekelem){ //adott index párosra beállít egy adott elemet
        this.jatekElemek[sor][oszlop] = jatekelem;
    }
}

