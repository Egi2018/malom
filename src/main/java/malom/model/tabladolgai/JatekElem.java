package malom.model.tabladolgai;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Ez az osztály felel a pályán fellelhető összes játék elemért.
 */
@Data
@AllArgsConstructor
public abstract class JatekElem {
    protected String nev;

    /**
     * @return Eldönti egy mezőről, hogy üres-e.
     */
    public abstract boolean uresE();
}