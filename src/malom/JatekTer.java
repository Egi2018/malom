package malom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
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
        JatekElem[][] jatekElemek = malomModel.getJatekElemek();
        List<Pozicio> szomszedok = malomModel.szomszedosSzabadCellak(utoljaraKattintott); //üres listába rakjuk, ha nincs utoljára kattintottunk
        for (int i = 0; i < jatekElemek.length; i += 1) {
            for (int j = 0; j < jatekElemek[0].length; j += 1) {
                setSzin(graphics, i, j);
                graphics.fillRect(j * SCALE, i * SCALE, SCALE, SCALE);
                if(jatekElemToSzin.containsKey(jatekElemek[i][j].nev))
                    graphics.setColor(jatekElemToSzin.get(jatekElemek[i][j].nev)); //az aktuális neve alapján megmondjuk a színét és be is állítjuk
                setUtoljaraKattintottSzin(graphics, i, j);
                setSzomszedSzin(graphics, szomszedok, i, j);
                graphics.fillOval(j * SCALE, i * SCALE, SCALE, SCALE);
            }
        }
        graphics.setColor(Color.red);
    }

    private void setUtoljaraKattintottSzin(Graphics graphics, int i, int j) {
        if (utoljaraKattintott != null && utoljaraKattintott.getSor() == i && utoljaraKattintott.getOszlop() == j) {
            graphics.setColor(red);
        }
    }

    private void setSzomszedSzin(Graphics graphics, List<Pozicio> szomszedok, int i, int j) {
        for (Pozicio szomszed : szomszedok) { //lekérdezi az utoljára kattintott szomszédait és megnézi, hogy
            if (szomszed.getSor() == i && szomszed.getOszlop() == j) {
                graphics.setColor(green);
            }
        }
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
            malomModel.setJatekElem(sor, oszlop, new FeketeKorong());
            utoljaraKattintott = new Pozicio(sor, oszlop);
            repaint();
        }
    }

}
