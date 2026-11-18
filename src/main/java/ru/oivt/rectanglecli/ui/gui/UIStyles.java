package ru.oivt.rectanglecli.ui.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public final class UIStyles {

    private UIStyles() {
        // Запрещаем создание экземпляров класса
    }

    // ==============================
    // Цветовая палитра
    // ==============================

    public static final Color BACKGROUND =
            new Color(245, 247, 250);

    public static final Color PRIMARY =
            new Color(35, 55, 85);

    public static final Color BUTTON =
            new Color(45, 110, 200);

    public static final Color WHITE =
            Color.WHITE;

    public static final Color TEXT =
            new Color(45, 55, 70);

    // ==============================
    // Шрифты
    // ==============================

    public static final Font TITLE_FONT =
            new Font("Arial", Font.BOLD, 26);

    public static final Font DEFAULT_FONT =
            new Font("Arial", Font.PLAIN, 14);

    public static final Font BUTTON_FONT =
            new Font("Arial", Font.BOLD, 14);

    // ==============================
    // Размеры окна
    // ==============================

    public static final int WINDOW_WIDTH = 750;
    public static final int WINDOW_HEIGHT = 500;

    public static final int MIN_WINDOW_WIDTH = 650;
    public static final int MIN_WINDOW_HEIGHT = 450;

    // ==============================
    // Отступы и рамки
    // ==============================

    public static final Border HEADER_BORDER =
            BorderFactory.createEmptyBorder(25, 10, 25, 10);

    public static final Border BOTTOM_BORDER =
            BorderFactory.createEmptyBorder(10, 15, 15, 15);

    // ==============================
    // Стилизация компонентов
    // ==============================

    public static void styleTitle(JLabel label) {
        label.setFont(TITLE_FONT);
        label.setForeground(PRIMARY);
    }

    public static void styleLabel(JLabel label) {
        label.setFont(DEFAULT_FONT);
        label.setForeground(TEXT);
    }

    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
    }

    public static void stylePrimaryButton(JButton button) {
        styleButton(button);
        button.setBackground(BUTTON);
        button.setForeground(WHITE);
        button.setOpaque(true);
    }

    public static void styleTextField(JTextField field) {
        field.setFont(DEFAULT_FONT);
    }

    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(DEFAULT_FONT);
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(BACKGROUND);
        panel.setBorder(HEADER_BORDER);

    }

    // ==============================
    // Создание компонентов
    // ==============================

    public static JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        styleLabel(label);

        return label;
    }

    public static JButton createButton(String text) {

        JButton button = new JButton(text);

        styleButton(button);

        return button;
    }

    public static JButton createPrimaryButton(String text) {

        JButton button = new JButton(text);

        stylePrimaryButton(button);

        return button;
    }

    public static JTextField createTextField(int columns) {

        JTextField field = new JTextField(columns);

        styleTextField(field);

        return field;
    }

    public static JComboBox<String> createComboBox(String[] items) {

        JComboBox<String> comboBox = new JComboBox<>(items);

        styleComboBox(comboBox);

        return comboBox;
    }
}