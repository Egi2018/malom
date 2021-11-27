package malom.model.jatekos;

import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.allapot.JatekosLerak;
import malom.model.tabladolgai.JatekElem;

/**
 * Az emberi játékos metódusai találhatók itt.
 */
public class EmberiJatekos extends Jatekos {

    /**
     * Az osztály konstruktora.
     *
     * @param palya     a malom pályája.
     * @param jatekElem Egy játék elem (ebben az esetben egy korong)
     */
    public EmberiJatekos(MalomModel palya, JatekElem jatekElem) {
        super(jatekElem);
        allapot = new JatekosLerak(palya);
    }

    /**
     * Ez a metódus elvégzi azokat a műveleteket amit az emberi játékosnál elkell végezni. (Ez azért üres mert az emberi játékos magának elvégzi a dolgokat, tehát ez a művelet majd a gépi játékosnál lesz fontos.)
     */
    @Override
    public void autoVegrehajt() {
    }

    /**
     * Ez a metódus felel azért, hogy haladjon a játékmenet amikor az emberi játékos van soron.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    @Override
    public void vegrehajt(Pozicio pozicio) {
        if (allapot.szabadE(pozicio)) {
            allapot.vegrehajt(pozicio);
            allapot.setKovetkezoAllapot(pozicio);
        }
    }
}

