package org.white5moke.ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel basePanel;

    public Window() {
        super("neurbotic");
        setSize(new Dimension(640, 440));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());

        buildOut();

        setVisible(true);
    }

    private void buildOut() {
        basePanel = new JPanel();

        add(BorderLayout.CENTER, basePanel);
    }
}
