package malom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.*;

public class Tabla extends JPanel {

    private static final int SCALE = 50;
    private static final int SOROK_SZAMA = 6;
    private static final int OSZLOPOK_SZAMA = 5;

    private Palya palya;
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
                palya.setJatekElem(e.getY() / 50, e.getX()/ 50, new FeketeKorong());
                repaint();
            }
        });
    }

    public void paint(Graphics graphics) {
        JatekElem[][] jatekElemek = palya.getJatekElemek();
        for (int i = 0; i < jatekElemek.length; i += 1) {
            for (int j = 0; j < jatekElemek[0].length; j += 1) {
                graphics.setColor(jatekElemToSzin.get(jatekElemek[i][j].nev)); //az aktuális neve alapján megmondjuk a színét és be is állítjuk
                graphics.fillRect(j * SCALE, i * SCALE, SCALE, SCALE);
            }
        }
        graphics.setColor(Color.red);
    }

    private void setSzin(Graphics graphics, int i, int j) {
        if (i % 2 == 0) {
            if (j % 2 == 0) graphics.setColor(white);
            else graphics.setColor(yellow);
        } else {
            if (j % 2 == 0) graphics.setColor(yellow);
            else graphics.setColor(white);
        }
    }

    public static void futtato() { //Ez indítja el a program megjelenítéseét és vezérli az kinézetét a programnak
        Tabla tabla= new Tabla();
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
