package malom.controller;

import malom.model.JatekElem;
import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.view.MalomView;

import java.util.List;
import java.util.Optional;

public class MalomController {
    private final MalomModel malomModel;
    private final MalomView malomView;

    public MalomController(MalomModel malomModel){ //ez szolgáltattja a dolgokat a view számára
        this.malomModel = malomModel;    //megkapunk kivülről egy pályát
        this.malomView = new MalomView(this, this.malomModel);
    }

    public JatekElem[][] getJatekElemek(){
        return this.malomModel.getJatekElemek();
    }

    public Optional<Pozicio> getIndulasiPozicio(){ //ha van érték a mezőn akkor nem null a visszatérési érték
        return Optional.ofNullable(this.malomModel.getIndulasiPozicio());
    }

    public List<Pozicio> getIndulasiPozicioSzomszedok(){
        return this.malomModel.getIndulasiPozicioSzomszedok();
    }

    public void vegrehajt(Pozicio pozicio) {
        malomModel.vegrehajt(pozicio);
        malomView.repaint();  //frissítjük a képet
    }
}
