package malom.model.jatekos;

import lombok.Data;
import malom.model.Pozicio;
import malom.model.allapot.Allapot;
import malom.model.tabladolgai.JatekElem;

/**
 * Ez a fő osztály ami a különböző tipusu játékosok metódusait hivatott elvégeztetni.
 */
@Data
public abstract class Jatekos {
    protected Allapot allapot;
    protected JatekElem jatekElem;
    private int korSzamlalo;

    /**
     * Az osztály konstruktora.
     *
     * @param jatekElem
     */
    public Jatekos(JatekElem jatekElem) {
        this.jatekElem = jatekElem;
        korSzamlalo = 0;
    }

    /**
     * Ez a metódus felel a feladatok autómatikus elvégzéséért.
     */
    public abstract void autoVegrehajt();

    /**
     * Ez a  metódus felel azért, hogy a müveletek végre legyenek hajtva.
     *
     * @param pozicio egy pályán lévő pozíció.
     */
    public abstract void vegrehajt(Pozicio pozicio);

    /**
     * Ez a metódus növeli a körszámlálót.
     */
    public void novelKorSzamlalo() {
        korSzamlalo++;
    }
}


