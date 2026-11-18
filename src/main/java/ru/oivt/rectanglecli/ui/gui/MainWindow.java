package ru.oivt.rectanglecli.ui.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ru.oivt.rectanglecli.service.CalculationHistory;
import ru.oivt.rectanglecli.service.CalculationResult;
import ru.oivt.rectanglecli.service.ShapeCalculator;
import ru.oivt.rectanglecli.util.InputUtils;

public class MainWindow extends JFrame {
	
	private JList<String> shapeList;
	private CardLayout cardLayout;
	private JPanel inputPanel;
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField sideField;
	private JTextField radiusField;
	private JTextField sideAField;
	private JTextField sideBField;
	private JTextField sideCField;
	
	private JTextArea resultArea;
	private JTextArea historyArea;

	private JButton calculateButton;
	private JButton clearButton;
	
	private CalculationHistory calculationHistory;
	private ShapeCalculator shapeCalculator;
	
    public MainWindow() {
    	shapeCalculator = new ShapeCalculator();
    	calculationHistory = new CalculationHistory();

        setTitle("Geometric GUI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(UIStyles.WINDOW_WIDTH, UIStyles.WINDOW_HEIGHT);
        setMinimumSize(new Dimension(
                UIStyles.MIN_WINDOW_WIDTH,
                UIStyles.MIN_WINDOW_HEIGHT
        ));

        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {

        setLayout(new BorderLayout(12, 12));

        getContentPane().setBackground(UIStyles.BACKGROUND);

        // Левая зона — выбор фигуры
        add(createSelectionPanel(), BorderLayout.WEST);

        // Центральная зона — ввод параметров
        add(createCenterPanel(), BorderLayout.CENTER);

        // Правая зона — результаты и история
        add(createRightPanel(), BorderLayout.EAST);

        // Обработка выбора фигуры
        shapeList.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                String selectedShape = shapeList.getSelectedValue();

                if (selectedShape != null) {
                    cardLayout.show(inputPanel, selectedShape);
                }
            }
        });
    }
    
    private JPanel createSelectionPanel() {

        JPanel panel = new JPanel(new BorderLayout(5, 15));

        UIStyles.stylePanel(panel);

        panel.setPreferredSize(new Dimension(270, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(UIStyles.PANEL);

        JLabel titleLine1 = UIStyles.createLabel("Геометрические");
        JLabel titleLine2 = UIStyles.createLabel("фигуры");

        titleLine1.setFont(UIStyles.BUTTON_FONT);
        titleLine2.setFont(UIStyles.BUTTON_FONT);

        titlePanel.add(titleLine1);
        titlePanel.add(titleLine2);

        shapeList = UIStyles.createList(new String[]{
                "Прямоугольник",
                "Квадрат",
                "Круг",
                "Треугольник"
        });

        shapeList.setSelectedIndex(0);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(shapeList, BorderLayout.CENTER);

        return panel;
    }
    
    private JPanel createCenterPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 20));

        UIStyles.stylePanel(panel);

        JLabel title = UIStyles.createLabel("Параметры фигуры");
        title.setFont(UIStyles.TITLE_FONT);

        initializeInputPanels();

        calculateButton = UIStyles.createPrimaryButton("Рассчитать");
        calculateButton.addActionListener(e -> calculate());
        
     // Запуск вычислений нажатием Enter
        lengthField.addActionListener(e -> calculate());
        widthField.addActionListener(e -> calculate());

        sideField.addActionListener(e -> calculate());

        radiusField.addActionListener(e -> calculate());

        sideAField.addActionListener(e -> calculate());
        sideBField.addActionListener(e -> calculate());
        sideCField.addActionListener(e -> calculate());
        
        getRootPane().setDefaultButton(calculateButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(calculateButton, BorderLayout.SOUTH);

        return panel;
    }
    
    private JPanel createRightPanel() {

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 12));

        UIStyles.stylePanel(panel);

        panel.setPreferredSize(new Dimension(350, 0));

        // Результаты
        JPanel resultPanel = new JPanel(new BorderLayout(5, 10));
        UIStyles.stylePanel(resultPanel);

        JLabel resultTitle = UIStyles.createLabel("Результаты");
        resultTitle.setFont(UIStyles.BUTTON_FONT);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        resultArea.setBackground(UIStyles.INPUT_BACKGROUND);
        resultArea.setForeground(UIStyles.WHITE);
        resultArea.setFont(UIStyles.SMALL_FONT);

        JScrollPane resultScroll = new JScrollPane(resultArea);

        clearButton = UIStyles.createButton("Очистить");
        clearButton.addActionListener(e -> clearFields());

        resultPanel.add(resultTitle, BorderLayout.NORTH);
        resultPanel.add(resultScroll, BorderLayout.CENTER);
        resultPanel.add(clearButton, BorderLayout.SOUTH);

        // История
        JPanel historyPanel = new JPanel(new BorderLayout(5, 10));
        UIStyles.stylePanel(historyPanel);

        JLabel historyTitle = UIStyles.createLabel("История вычислений");
        historyTitle.setFont(UIStyles.BUTTON_FONT);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);

        historyArea.setBackground(UIStyles.INPUT_BACKGROUND);
        historyArea.setForeground(UIStyles.WHITE);
        historyArea.setFont(UIStyles.SMALL_FONT);

        JScrollPane historyScroll = new JScrollPane(historyArea);

        historyPanel.add(historyTitle, BorderLayout.NORTH);
        historyPanel.add(historyScroll, BorderLayout.CENTER);

        panel.add(resultPanel);
        panel.add(historyPanel);

        return panel;
    }
    
    private JPanel createRectanglePanel() {

        JPanel panel = new JPanel(new GridLayout(2, 2, 12, 15));

        UIStyles.stylePanel(panel);

        lengthField = UIStyles.createTextField(12);
        widthField = UIStyles.createTextField(12);

        JLabel lengthLabel = UIStyles.createLabel("Длина:");
        JLabel widthLabel = UIStyles.createLabel("Ширина:");

        panel.add(lengthLabel);
        panel.add(lengthField);

        panel.add(widthLabel);
        panel.add(widthField);

        return panel;
    }

    private JPanel createSquarePanel() {

        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 15));

        UIStyles.stylePanel(panel);

        sideField = UIStyles.createTextField(12);

        JLabel sideLabel = UIStyles.createLabel("Сторона:");

        panel.add(sideLabel);
        panel.add(sideField);

        return panel;
    }

    private JPanel createCirclePanel() {

        JPanel panel = new JPanel(new GridLayout(1, 2, 12, 15));

        UIStyles.stylePanel(panel);

        radiusField = UIStyles.createTextField(12);

        JLabel radiusLabel = UIStyles.createLabel("Радиус:");

        panel.add(radiusLabel);
        panel.add(radiusField);

        return panel;
    }

    private JPanel createTrianglePanel() {

        JPanel panel = new JPanel(new GridLayout(3, 2, 12, 15));

        UIStyles.stylePanel(panel);

        sideAField = UIStyles.createTextField(12);
        sideBField = UIStyles.createTextField(12);
        sideCField = UIStyles.createTextField(12);

        JLabel sideALabel = UIStyles.createLabel("Сторона A:");
        JLabel sideBLabel = UIStyles.createLabel("Сторона B:");
        JLabel sideCLabel = UIStyles.createLabel("Сторона C:");

        panel.add(sideALabel);
        panel.add(sideAField);

        panel.add(sideBLabel);
        panel.add(sideBField);

        panel.add(sideCLabel);
        panel.add(sideCField);

        return panel;
    }
    
    private void initializeInputPanels() {

        cardLayout = new CardLayout();

        inputPanel = new JPanel(cardLayout);
        inputPanel.setBackground(UIStyles.BACKGROUND);

        inputPanel.add(createRectanglePanel(), "Прямоугольник");
        inputPanel.add(createSquarePanel(), "Квадрат");
        inputPanel.add(createCirclePanel(), "Круг");
        inputPanel.add(createTrianglePanel(), "Треугольник");

        cardLayout.show(inputPanel, "Прямоугольник");
    }
   
    private void calculate() {

        try {

            String selectedShape = shapeList.getSelectedValue();

            if (selectedShape == null) {
                return;
            }

            CalculationResult result;

            switch (selectedShape) {

                case "Прямоугольник":
                    result = shapeCalculator.calculateRectangle(
                            readValue(lengthField),
                            readValue(widthField)
                    );
                    break;

                case "Квадрат":
                    result = shapeCalculator.calculateSquare(
                            readValue(sideField)
                    );
                    break;

                case "Круг":
                    result = shapeCalculator.calculateCircle(
                            readValue(radiusField)
                    );
                    break;

                case "Треугольник":
                    result = shapeCalculator.calculateTriangle(
                            readValue(sideAField),
                            readValue(sideBField),
                            readValue(sideCField)
                    );
                    break;

                default:
                    return;
            }

            StringBuilder output = new StringBuilder();

            output.append(result.getShapeName())
                  .append("\n\n");

            for (var entry : result.getValues().entrySet()) {

                output.append(entry.getKey())
                      .append(": ");

                Object value = entry.getValue();

                if (value instanceof Number) {
                    output.append(String.format(
                            "%.4f",
                            ((Number) value).doubleValue()
                    ));
                } else {
                    output.append(value);
                }

                output.append("\n");
            }

            resultArea.setText(output.toString());

            // Добавляем вычисление в историю
            calculationHistory.add(result);

            historyArea.setText(
                    calculationHistory.getFormattedHistory()
            );

            // Прокручиваем историю к последнему вычислению
            historyArea.setCaretPosition(
                    historyArea.getDocument().getLength()
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private double readValue(JTextField field) {
        return InputUtils.parsePositiveDouble(field.getText());
    }
        
    private void clearFields() {

        lengthField.setText("");
        widthField.setText("");

        sideField.setText("");
        radiusField.setText("");

        sideAField.setText("");
        sideBField.setText("");
        sideCField.setText("");

        resultArea.setText("");

        switch (shapeList.getSelectedValue()) {

            case "Прямоугольник":
                lengthField.requestFocusInWindow();
                break;

            case "Квадрат":
                sideField.requestFocusInWindow();
                break;

            case "Круг":
                radiusField.requestFocusInWindow();
                break;

            case "Треугольник":
                sideAField.requestFocusInWindow();
                break;
        }
    }
}
