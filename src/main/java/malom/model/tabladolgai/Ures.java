package malom.model.tabladolgai;

/**
 * Ez az üres helyeknek az osztálya, amik a pályán találhatók.
 */
public class Ures extends JatekElem {//Öröklés

    public Ures() {
        super("ures");  //Meghíja az ős osztály konstruktorát
    }

    /**
     * @return Eldönti egy mezőről, hogy üres-e. (Ebben az esetben azért igaz, hogy üres,mivel ez az Ures osztály függvénye.)
     */
    @Override
    public boolean uresE() {
        return true;
    }
}
