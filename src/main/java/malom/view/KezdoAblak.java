package malom.view;

import malom.controller.MalomController;
import malom.model.MalomModel;

import javax.swing.*;
import java.awt.*;

public class KezdoAblak extends JFrame {

    private JComboBox<String> ellenfel;
    private JButton start;
    public KezdoAblak() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Valasztas");
        this.setVisible(true);
        initUI();
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(ellenfel);
        container.add(start);
        pack();
    }

    private void initUI() {
        ellenfel = new JComboBox<>(new String[]{"ember", "gep"});
        start = new JButton("start");
        start.addActionListener(e ->
                new MalomController(new MalomModel((String) ellenfel.getSelectedItem())));
    }
}
