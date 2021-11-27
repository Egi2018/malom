package malom.view;

import malom.controller.MalomController;
import malom.model.MalomModel;

import javax.swing.*;
import java.awt.*;

/**
 * A megjelenő főablak metódusaitnak osztálya.
 */
public class MalomView extends JFrame  implements ModelValtozottListener, JatekVegeListener{

    public MalomView(MalomController malomController, MalomModel malomModel) {  //megjelenito
        JatekTer jatekTer = new JatekTer(malomController);
        this.setSize(600, 600);
        this.getContentPane().add(jatekTer);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Malom");
        this.setVisible(true);
        malomModel.regisztralJatekVegeListener(this);
        malomModel.regisztralModelValtozottListener(this);
    }

    /**
     * Ez a metódus akkor kerül meghívásra ha, a pályán változott valami és a változást látni is kell.
     */
    @Override
    public void modelValtozott() {
        repaint();
    }

    /**
     * Ez a megtódus mejelenít egy ujabb ablakot majd lezárja a programot.
     */
    @Override
    public void befejezJatek(){
        repaint();
        JOptionPane.showMessageDialog(this,
                "Nyertél","Játék vége", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}
