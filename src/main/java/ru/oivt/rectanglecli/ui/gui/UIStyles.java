package ru.oivt.rectanglecli.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListCellRenderer;
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
	         new Color(23, 26, 33);
	
	 public static final Color PANEL =
	         new Color(34, 39, 51);
	
	 public static final Color INPUT_BACKGROUND =
	         new Color(43, 50, 64);
	
	 public static final Color PRIMARY =
	         new Color(91, 141, 239);
	
	 public static final Color BUTTON =
	         new Color(52, 120, 246);
	
	 public static final Color WHITE =
	         new Color(230, 234, 240);
	
	 public static final Color TEXT =
	         new Color(230, 234, 240);
	
	 public static final Color MUTED =
	         new Color(146, 155, 173);
	
	 public static final Color BORDER =
	         new Color(58, 66, 82);

    // ==============================
    // Шрифты
    // ==============================

	 private static final Font PIXEL_FONT = loadPixelFont();

	 public static final Font TITLE_FONT =
	         PIXEL_FONT.deriveFont(Font.BOLD, 26f);

	 public static final Font DEFAULT_FONT =
	         PIXEL_FONT.deriveFont(Font.PLAIN, 14f);

	 public static final Font BUTTON_FONT =
	         PIXEL_FONT.deriveFont(Font.BOLD, 14f);
	 
	 public static final Font SMALL_FONT =
		        new Font("Monospaced", Font.BOLD, 14);

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
        field.setForeground(WHITE);
        field.setBackground(INPUT_BACKGROUND);
        field.setCaretColor(WHITE);
        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        BorderFactory.createEmptyBorder(7, 8, 7, 8)
                )
        );
    }

    public static void styleList(JList<String> list) {

        list.setFont(DEFAULT_FONT);
        list.setForeground(WHITE);
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
            System.err.println("Не удалось загрузить пиксельный шрифт: "
                    + e.getMessage());

            return new Font("Monospaced", Font.PLAIN, 14);
        }
    }
}