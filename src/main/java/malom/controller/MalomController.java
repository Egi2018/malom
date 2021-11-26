package malom.controller;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;
import malom.view.MalomView;

import java.util.List;
import java.util.Optional;
/** Ez az osztály felel azért, hogy irányítsa a többi osztály által létrehozott metódusokat, illetve ez köti majd össze a megjelenítéssel és a megvalósítással kapcsolatos osztályokat. (Magyarul ez az osztály irányítja  a többit) */
public class MalomController {
    private final MalomModel malomModel;
    private final MalomView malomView;
    /** A MalomController osztály konstruktora ami beállít egy kivülről kapott pályát és össze köti ezt a megjelenítésért felelős osztállyal. */
    public MalomController(MalomModel malomModel){ //ez szolgáltattja a dolgokat a view számára
        this.malomModel = malomModel;    //megkapunk kivülről egy pályát
        this.malomView = new MalomView(this, this.malomModel);
    }
    /** Lekéri egy adott pozición a játék elemeket. */
    public JatekElem[][] getJatekElemek(){
        return this.malomModel.getJatekElemek();
    }
    /** Lekér egy indulási poziciót és ha van érték a mezőn, akkor nem null a visszatérési értéke. */
    public Optional<Pozicio> getIndulasiPozicio(){ //ha van érték a mezőn akkor nem null a visszatérési érték
        return Optional.ofNullable(this.malomModel.getIndulasiPozicio());
    }
    /** Lekéri a lehetséges szomszédos irányokat. */
    public List<Pozicio> getIndulasiPozicioSzomszedok(){
        return this.malomModel.getIndulasiPozicioSzomszedok();
    }
    /** Egy adott poziciót megkapunk és attól függően, hogy épp melyik fázisban vagyunk végrehajtjuk az adott fázisban végrehajtandó feladatot és vrissítjük a képet. */
    public void vegrehajt(Pozicio pozicio) {
        malomModel.vegrehajt(pozicio);
        malomView.repaint();  //frissítjük a képet
    }
}
