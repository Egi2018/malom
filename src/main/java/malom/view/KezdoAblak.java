package malom.view;

import malom.controller.MalomController;
import malom.model.MalomModel;

import javax.swing.*;
import java.awt.*;

public class KezdoAblak extends JFrame {

    private JComboBox<String> ellenfelTipusCombo;

    public KezdoAblak() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Valasztas");
        this.setVisible(true);
        initUI();
    }

    private void initUI() {
        ellenfelTipusCombo = new JComboBox<>(new String[]{"ember", "gep"});
        JButton startButton = new JButton("start");
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(ellenfelTipusCombo);
        container.add(startButton);
        pack();
        startButton.addActionListener(e ->
                new MalomController(new MalomModel((String) ellenfelTipusCombo.getSelectedItem())));
    }
}
