package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.GepLerak;
import malom.model.tabladolgai.JatekElem;

/**
 * A gépi játékos metódusait hivatott irányítani.
 */
public class GepiJatekos extends Jatekos {

    /**
     * Az osztály konstruktora.
     *
     * @param palya     a malom játék pályája.
     * @param jatekElem egy játék elem.
     */
    public GepiJatekos(MalomModel palya, JatekElem jatekElem) {
        super(jatekElem);
        allapot = new GepLerak(palya);
    }

    /**
     * Elíndítja a gépi játékos metódusait.
     */
    @Override
    public void autoVegrehajt() {
        if (allapot.szabadE(null)) {
            allapot.vegrehajt(null);
            allapot.setKovetkezoAllapot(null);
        }
    }

    /**
     * Ez a metódus lenne az ahol végrehajthatnánk a végrehajtadó dolgokat, de ezek autómatán történnek a gépi játékos módban.
     *
     * @param pozicio egy pályán levő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
    }
}
