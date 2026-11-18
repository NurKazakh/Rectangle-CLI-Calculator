package ru.oivt.rectanglecli.ui.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public final class UIStyles {

    private UIStyles() {
        // Запрещаем создание экземпляров класса
    }

    // ==============================
    // Цветовые палитры
    // ==============================

    // Dark theme

    private static final Color DARK_BACKGROUND =
            new Color(23, 26, 33);

    private static final Color DARK_PANEL =
            new Color(34, 39, 51);

    private static final Color DARK_INPUT_BACKGROUND =
            new Color(43, 50, 64);

    private static final Color DARK_PRIMARY =
            new Color(91, 141, 239);

    private static final Color DARK_BUTTON =
            new Color(52, 120, 246);

    private static final Color DARK_WHITE =
            new Color(230, 234, 240);

    private static final Color DARK_TEXT =
            new Color(230, 234, 240);

    private static final Color DARK_MUTED =
            new Color(146, 155, 173);

    private static final Color DARK_BORDER =
            new Color(58, 66, 82);


    // Light theme

    private static final Color LIGHT_BACKGROUND =
            new Color(240, 243, 248);

    private static final Color LIGHT_PANEL =
            new Color(255, 255, 255);

    private static final Color LIGHT_INPUT_BACKGROUND =
            new Color(248, 249, 252);

    private static final Color LIGHT_PRIMARY =
            new Color(65, 105, 225);

    private static final Color LIGHT_BUTTON =
            new Color(52, 120, 246);

    private static final Color LIGHT_WHITE =
            new Color(255, 255, 255);

    private static final Color LIGHT_TEXT =
            new Color(35, 42, 55);

    private static final Color LIGHT_MUTED =
            new Color(110, 120, 135);

    private static final Color LIGHT_BORDER =
            new Color(215, 220, 230);


    // ==============================
    // Текущая тема
    // ==============================

    private static boolean darkTheme = true;

    public static Color BACKGROUND = DARK_BACKGROUND;
    public static Color PANEL = DARK_PANEL;
    public static Color INPUT_BACKGROUND = DARK_INPUT_BACKGROUND;
    public static Color PRIMARY = DARK_PRIMARY;
    public static Color BUTTON = DARK_BUTTON;
    public static Color WHITE = DARK_WHITE;
    public static Color TEXT = DARK_TEXT;
    public static Color MUTED = DARK_MUTED;
    public static Color BORDER = DARK_BORDER;


    // ==============================
    // Переключение темы
    // ==============================

    public static void setDarkTheme(boolean enabled) {

        darkTheme = enabled;

        if (darkTheme) {

            BACKGROUND = DARK_BACKGROUND;
            PANEL = DARK_PANEL;
            INPUT_BACKGROUND = DARK_INPUT_BACKGROUND;
            PRIMARY = DARK_PRIMARY;
            BUTTON = DARK_BUTTON;
            WHITE = DARK_WHITE;
            TEXT = DARK_TEXT;
            MUTED = DARK_MUTED;
            BORDER = DARK_BORDER;

        } else {

            BACKGROUND = LIGHT_BACKGROUND;
            PANEL = LIGHT_PANEL;
            INPUT_BACKGROUND = LIGHT_INPUT_BACKGROUND;
            PRIMARY = LIGHT_PRIMARY;
            BUTTON = LIGHT_BUTTON;
            WHITE = LIGHT_WHITE;
            TEXT = LIGHT_TEXT;
            MUTED = LIGHT_MUTED;
            BORDER = LIGHT_BORDER;
        }
    }

    public static boolean isDarkTheme() {
        return darkTheme;
    }


    // ==============================
    // Шрифты
    // ==============================

    private static final Font PIXEL_FONT = loadPixelFont();

    public static final Font TITLE_FONT =
            PIXEL_FONT.deriveFont(Font.BOLD, 26f);

    public static final Font DEFAULT_FONT =
            PIXEL_FONT.deriveFont(Font.BOLD, 14);

    public static final Font BUTTON_FONT =
            PIXEL_FONT.deriveFont(Font.BOLD, 14f);
    
    public static final Font HEAD_FONT =
            PIXEL_FONT.deriveFont(Font.BOLD, 18f);

    public static final Font SMALL_FONT =
            new Font("Monospaced", Font.BOLD, 18);


    // ==============================
    // Размеры окна
    // ==============================

    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 700;

    public static final int MIN_WINDOW_WIDTH = 950;
    public static final int MIN_WINDOW_HEIGHT = 600;


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
        label.setForeground(WHITE);
    }

    public static void styleLabel(JLabel label) {

        label.setFont(DEFAULT_FONT);
        label.setForeground(TEXT);
    }

    public static void styleButton(JButton button) {

        button.setFont(BUTTON_FONT);
        button.setForeground(TEXT);
        button.setBackground(INPUT_BACKGROUND);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        BorderFactory.createEmptyBorder(8, 14, 8, 14)
                )
        );
    }

    public static void stylePrimaryButton(JButton button) {

        styleButton(button);

        button.setBackground(BUTTON);
        button.setForeground(WHITE);

        button.setBorder(
                BorderFactory.createEmptyBorder(9, 18, 9, 18)
        );
    }

    public static void styleTextField(JTextField field) {

        field.setFont(DEFAULT_FONT);
        field.setForeground(TEXT);
        field.setBackground(INPUT_BACKGROUND);
        field.setCaretColor(TEXT);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        BorderFactory.createEmptyBorder(4, 8, 4, 8)                )
        );
    }

    public static void styleList(JList<String> list) {

        list.setFont(DEFAULT_FONT);
        list.setForeground(TEXT);
        list.setBackground(PANEL);

        list.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        list.setFixedCellHeight(45);

        list.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        list.setSelectionBackground(BUTTON);
        list.setSelectionForeground(WHITE);

        list.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus
            ) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                label.setBorder(
                        BorderFactory.createEmptyBorder(5, 12, 5, 12)
                );

                label.setOpaque(true);

                if (isSelected) {

                    label.setBackground(BUTTON);
                    label.setForeground(WHITE);

                } else {

                    label.setBackground(PANEL);
                    label.setForeground(TEXT);
                }

                return label;
            }
        });
    }

    public static void stylePanel(JPanel panel) {

        panel.setBackground(PANEL);

        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );
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

    public static JList<String> createList(String[] items) {

        JList<String> list = new JList<>(items);

        styleList(list);

        return list;
    }


    // ==============================
    // Загрузка шрифта
    // ==============================

    private static Font loadPixelFont() {

        try (InputStream stream = UIStyles.class.getResourceAsStream(
                "/fonts/PressStart2P-Regular.ttf")) {

            if (stream == null) {
                throw new IOException("Файл шрифта не найден");
            }

            return Font.createFont(Font.TRUETYPE_FONT, stream);

        } catch (FontFormatException | IOException e) {

            System.err.println(
                    "Не удалось загрузить пиксельный шрифт: "
                            + e.getMessage()
            );

            return new Font("Monospaced", Font.PLAIN, 14);
        }
    }
}