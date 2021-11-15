package malom;

import javax.swing.*;
import java.awt.*;

public class MalomView extends JFrame {

    public MalomView(MalomController malomController, MalomModel malomModel) {  //megjelenito
        JatekTer jatekTer = new JatekTer(malomController, malomModel);
        this.setSize(600, 600);
        this.getContentPane().add(jatekTer);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Malom");
        this.setVisible(true);
    }
}
