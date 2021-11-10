package malom;

public class Ures extends JatekElem{//Öröklés
    public Ures() {
        super("ures");  //Meghíja az ős osztály konstruktorát
    }

    @Override
    public boolean ures() {
        return true;
    }
}
