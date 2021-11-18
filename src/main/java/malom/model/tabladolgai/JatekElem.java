package malom.model.tabladolgai;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class JatekElem {
    protected String nev;

    public abstract boolean uresE();
}