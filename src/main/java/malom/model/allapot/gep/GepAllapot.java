package malom.model.allapot.gep;

import malom.model.MalomModel;
import malom.model.Pozicio;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static malom.model.Pozicio.of;

public abstract class GepAllapot {
    protected static final int MAX_KORSZAM = 6;

    protected MalomModel palya;

    public GepAllapot(MalomModel palya) {
        this.palya = palya;
    }

    public abstract void vegrehajt();
}
