package ru.oivt.rectanglecli.ui.gui;

import javax.swing.SwingUtilities;

public class GUIApplication {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });

    }
}
