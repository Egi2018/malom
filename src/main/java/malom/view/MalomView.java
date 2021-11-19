package malom.view;

import malom.controller.MalomController;
import malom.model.MalomModel;

import javax.swing.*;
import java.awt.*;

public class MalomView extends JFrame  implements ModelValtozottListener, JatekVegeListener{

    public MalomView(MalomController malomController, MalomModel malomModel) {  //megjelenito
        JatekTer jatekTer = new JatekTer(malomController, malomModel);
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

    @Override
    public void modelValtozott() {
        repaint();
    }

    @Override
    public void befejezJatek(){
        repaint();
        JOptionPane.showMessageDialog(this,
                "Nyertél","Játék vége", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}
