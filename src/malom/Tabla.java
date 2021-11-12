package malom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.Color.*;

public class Tabla extends JPanel {

    private static final int SCALE = 50;

    private Palya palya;
    private Pozicio utoljaraKattintott;
    private Map<String, Color> jatekElemToSzin;

    public Tabla() {
        jatekElemToSzin = new HashMap<>();
        jatekElemToSzin.put("ures", gray);
        jatekElemToSzin.put("feher", white);
        jatekElemToSzin.put("fekete", black);
        palya = new Palya();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int sor = e.getY() / 50;
                int oszlop = e.getX() / 50;
                palya.setJatekElem(sor, oszlop, new FeketeKorong());
                utoljaraKattintott = new Pozicio(sor, oszlop);
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics graphics) {
        JatekElem[][] jatekElemek = palya.getJatekElemek();
        List<Pozicio> szomszedok = utoljaraKattintott == null ? new ArrayList<>() : palya.szomszedosSzabadCellak(utoljaraKattintott); //üres listába rakjuk, ha nincs utoljára kattintottunk
        for (int i = 0; i < jatekElemek.length; i += 1) {
            for (int j = 0; j < jatekElemek[0].length; j += 1) {
                graphics.setColor(jatekElemToSzin.get(jatekElemek[i][j].nev)); //az aktuális neve alapján megmondjuk a színét és be is állítjuk
                if (utoljaraKattintott != null && utoljaraKattintott.getSor() == i && utoljaraKattintott.getOszlop() == j) {
                    graphics.setColor(red);
                }
                for (Pozicio szomszed : szomszedok) { //lekérdezi az utoljára kattintott szomszédait és megnézi, hogy
                    if (szomszed.getSor() == i && szomszed.getOszlop() == j) {
                        graphics.setColor(green);
                    }
                }
                graphics.fillRect(j * SCALE, i * SCALE, SCALE, SCALE);
            }
        }
        graphics.setColor(Color.red);
    }

    public static void futtato() { //Ez indítja el a program megjelenítéseét és vezérli az kinézetét a programnak
        Tabla tabla = new Tabla();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.getContentPane().add(tabla);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Malom");
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        futtato();
    }
}
