package malom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Color.*;

public class Tabla extends JPanel {

    private static final int SCALE = 50;
    private static final int SOROK_SZAMA = 6;
    private static final int OSZLOPOK_SZAMA = 5;

    public Tabla() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX()+" "+e.getY());
                repaint(e.getX() / 50 * 50, e.getY()/ 50 * 50, SCALE, SCALE);
                System.out.println(e.getX() / 50*50+" "+e.getY()/50*50);
            }
        });
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < SOROK_SZAMA; i += 1) {
            for (int j = 0; j < OSZLOPOK_SZAMA; j += 1) {
                setSzin(graphics, i, j);
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
