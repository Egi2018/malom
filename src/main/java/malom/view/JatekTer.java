package malom.view;

import malom.controller.MalomController;
import malom.model.MalomModel;
import malom.model.Pozicio;
import malom.model.tabladolgai.JatekElem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;

public class JatekTer extends JPanel {

    private static final int SCALE = 75;

    private final MalomController malomController;
    private final MalomModel malomModel;
    private Pozicio utoljaraKattintott;
    private final Map<String, Color> jatekElemToSzin;

    public JatekTer(MalomController malomController, MalomModel malomModel) {
        jatekElemToSzin = new HashMap<>();
        jatekElemToSzin.put("feher", white);
        jatekElemToSzin.put("fekete", black);
        this.malomController = malomController;
        this.malomModel = malomModel;
        this.addMouseListener(new MouseReleasedListener());
    }

    @Override
    public void paint(Graphics graphics) {
        JatekElem[][] jatekElemek = malomController.getJatekElemek();
        for (int i = 0; i < jatekElemek.length; i += 1) {
            for (int j = 0; j < jatekElemek[0].length; j += 1) {
                setSzin(graphics, i, j);
                graphics.fillRect(j * SCALE, i * SCALE, SCALE, SCALE);
                if(jatekElemToSzin.containsKey(jatekElemek[i][j].getNev())) {
                    graphics.setColor(jatekElemToSzin.get(jatekElemek[i][j].getNev())); //az aktuális neve alapján megmondjuk a színét és be is állítjuk
                    graphics.fillOval(j * SCALE, i * SCALE, SCALE, SCALE);
                }
            }
        }
        malomController.getIndulasiPozicio()
                        .ifPresent(pozicio -> {
                            graphics.setColor(red);
                            graphics.fillOval(pozicio.getOszlop() * SCALE, pozicio.getSor() * SCALE, SCALE, SCALE);
                        });

        malomController.getIndulasiPozicioSzomszedok()
                .forEach(pozicio -> {
                    graphics.setColor(green);
                    graphics.fillOval(pozicio.getOszlop() * SCALE, pozicio.getSor() * SCALE, SCALE, SCALE);
                });
    }

    private void setSzin(Graphics graphics, int i, int j) {
        if (i % 2 == 0) {
            if (j % 2 == 0) graphics.setColor(darkGray);
            else graphics.setColor(gray);
        } else {
            if (j % 2 == 0) graphics.setColor(gray);
            else graphics.setColor(darkGray);
        }
    }

    class MouseReleasedListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e) {
            int sor = e.getY() / SCALE;
            int oszlop = e.getX() / SCALE;
            malomController.vegrehajt(new Pozicio(sor, oszlop));
        }
    }

}
