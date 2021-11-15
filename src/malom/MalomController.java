package malom;

public class MalomController {
    private MalomModel malomModel;
    private MalomView malomView;

    public MalomController(MalomModel malomModel){ //ez szolgáltattja a dolgokat a view számára
        this.malomModel = malomModel;    //megkapunk kivülről egy pályát
        this.malomView = new MalomView(this, this.malomModel);
    }

}
