package malom.view;

import malom.controller.MalomController;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;

/**
 * A malom játék megjelenítéséért felelős osztály.
 */
public class JatekTer extends JPanel {

    private static final int MEZO = 75;

    private final MalomController malomController;
    private final Map<String, Color> jatekElemToSzin;

    /**
     * Az osztály konstruktora.
     * @param malomController A játék irányításáért felelős osztály egy tagja.
     */
    public JatekTer(MalomController malomController) {
        jatekElemToSzin = new HashMap<>();
        jatekElemToSzin.put("feher", white);
        jatekElemToSzin.put("fekete", black);
        this.malomController = malomController;
        this.addMouseListener(new MouseReleasedListener());
    }

    /**
     * A pálya kirajzolásáért felelős osztály.
     * @param graphics Grafikus megjelenítéshez szükséges paraméter.
     */
    @Override
    public void paint(Graphics graphics) {
        JatekElem[][] jatekElemek = malomController.getJatekElemek();
        for (int i = 0; i < jatekElemek.length; i += 1) {
            for (int j = 0; j < jatekElemek[0].length; j += 1) {
                setSzin(graphics, i, j);
                graphics.fillRect(j * MEZO, i * MEZO, MEZO, MEZO);
                if(jatekElemToSzin.containsKey(jatekElemek[i][j].getNev())) {
                    graphics.setColor(jatekElemToSzin.get(jatekElemek[i][j].getNev())); //az aktuális neve alapján megmondjuk a színét és be is állítjuk
                    graphics.fillOval(j * MEZO, i * MEZO, MEZO, MEZO);
                }
            }
        }
        malomController.getIndulasiPozicio()
                        .ifPresent(pozicio -> {
                            graphics.setColor(red);
                            graphics.fillOval(pozicio.getOszlop() * MEZO, pozicio.getSor() * MEZO, MEZO, MEZO);
                        });

        malomController.getIndulasiPozicioSzomszedok()
                .forEach(pozicio -> {
                    graphics.setColor(green);
                    graphics.fillOval(pozicio.getOszlop() * MEZO, pozicio.getSor() * MEZO, MEZO, MEZO);
                });
    }

    /**
     * A pálya mezőinek színeit állítja be.
     * @param graphics Grafikus megjelenítéshez szükséges paraméter.
     * @param i Egy adott sor sorszáma.
     * @param j Egy adott oszlop sorszáma.
     */
    private void setSzin(Graphics graphics, int i, int j) {
        if (i % 2 == 0) {
            if (j % 2 == 0) graphics.setColor(darkGray);
            else graphics.setColor(gray);
        } else {
            if (j % 2 == 0) graphics.setColor(gray);
            else graphics.setColor(darkGray);
        }
    }

    /**
     * Az egérkattintások kezelését hivatott elvégezni.
     */
    class MouseReleasedListener extends MouseAdapter{
        /**
         *  Beállítja, hogy hol történt az egér kattintás.
         * @param e Egy egér kattintás.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            int sor = e.getY() / MEZO;
            int oszlop = e.getX() / MEZO;
            malomController.vegrehajt(new Pozicio(sor, oszlop));
        }
    }

}
