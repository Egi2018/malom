package malom;

import java.util.List;

public class MalomController {
    private MalomModel malomModel;
    private MalomView malomView;

    public MalomController(MalomModel malomModel){ //ez szolgáltattja a dolgokat a view számára
        this.malomModel = malomModel;    //megkapunk kivülről egy pályát
        this.malomView = new MalomView(this, this.malomModel);
    }

    public JatekElem[][] getJatekElemek(){
        return this.malomModel.getJatekElemek();
    }

    public List<Pozicio> szomszedosSzabadCellak(Pozicio pozicio){
        return this.malomModel.szomszedosSzabadCellak(pozicio);
    }

    public void setJatekElem(Pozicio pozicio, JatekElem jatekElem){
        this.malomModel.setJatekElem(pozicio, jatekElem);
    }

}
