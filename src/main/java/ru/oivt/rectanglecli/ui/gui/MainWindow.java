package ru.oivt.rectanglecli.ui.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.oivt.rectanglecli.service.CalculationHistory;
import ru.oivt.rectanglecli.service.ShapeCalculator;
import ru.oivt.rectanglecli.util.InputUtils;

public class MainWindow extends JFrame {
	
	private JComboBox<String> shapeComboBox;

	private CardLayout cardLayout;
	private JPanel inputPanel;
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField sideField;
	private JTextField radiusField;
	private JTextField sideAField;
	private JTextField sideBField;
	private JTextField sideCField;
	
	private JLabel resultLabel;
	private JButton calculateButton;
	
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

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(UIStyles.BACKGROUND);

        JLabel titleLabel = new JLabel(
                "Geometry Calculator",
                SwingConstants.CENTER
        );
        UIStyles.styleTitle(titleLabel);

        JPanel headerPanel = new JPanel();
        UIStyles.stylePanel(headerPanel);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.PAGE_START);

        initializeInputPanels();

        JPanel selectionPanel = new JPanel();
        selectionPanel.setBackground(UIStyles.BACKGROUND);

        JLabel shapeLabel = UIStyles.createLabel("Выберите фигуру:");

        shapeComboBox = UIStyles.createComboBox(new String[]{
                "Прямоугольник",
                "Квадрат",
                "Круг",
                "Треугольник"
        });

        selectionPanel.add(shapeLabel);
        selectionPanel.add(shapeComboBox);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(UIStyles.BACKGROUND);
        bottomPanel.setBorder(UIStyles.BOTTOM_BORDER);
        bottomPanel.add(selectionPanel, BorderLayout.NORTH);
        bottomPanel.add(createResultPanel(), BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        shapeComboBox.addActionListener(e -> {

            String selectedShape =
                    (String) shapeComboBox.getSelectedItem();

            cardLayout.show(inputPanel, selectedShape);
        });
    }
    
    private JPanel createRectanglePanel() {

        JPanel panel = new JPanel();

        lengthField = UIStyles.createTextField(10);
        widthField = UIStyles.createTextField(10);

        JLabel lengthLabel = UIStyles.createLabel("Длина:");
        JLabel widthLabel = UIStyles.createLabel("Ширина:");

        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(widthLabel);
        panel.add(widthField);

        return panel;
    }

    private JPanel createSquarePanel() {

        JPanel panel = new JPanel();

        sideField = UIStyles.createTextField(10);

        JLabel sideLabel = UIStyles.createLabel("Сторона:");
        
        panel.add(sideLabel);
        panel.add(sideField);

        return panel;
    }

    private JPanel createCirclePanel() {

        JPanel panel = new JPanel();
        
        radiusField = UIStyles.createTextField(10);

        JLabel radiusLabel = UIStyles.createLabel("Радиус:");

        panel.add(radiusLabel);
        panel.add(radiusField);

        return panel;
    }

    private JPanel createTrianglePanel() {

        JPanel panel = new JPanel();

        sideAField = UIStyles.createTextField(5);
        sideBField = UIStyles.createTextField(5);
        sideCField = UIStyles.createTextField(5);

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

        inputPanel.add(createRectanglePanel(), "Прямоугольник");
        inputPanel.add(createSquarePanel(), "Квадрат");
        inputPanel.add(createCirclePanel(), "Круг");
        inputPanel.add(createTrianglePanel(), "Треугольник");

        add(inputPanel, BorderLayout.CENTER);

        cardLayout.show(inputPanel, "Прямоугольник");
    }

    private JPanel createResultPanel() {

        JPanel panel = new JPanel();
        UIStyles.stylePanel(panel);

        resultLabel = UIStyles.createLabel(
                "Площадь: -    Периметр: -"
        );

        calculateButton = UIStyles.createPrimaryButton("Рассчитать");

        JButton historyButton = UIStyles.createButton("История");
        JButton clearButton = UIStyles.createButton("Очистить");

        calculateButton.addActionListener(e -> calculate());
        historyButton.addActionListener(e -> showHistory());
        clearButton.addActionListener(e -> clearFields());

        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(historyButton);
        panel.add(clearButton);

        return panel;
    }
    
    private void calculate() {

        try {

            String selectedShape =
                    (String) shapeComboBox.getSelectedItem();

            double area;
            double perimeter;
            double[] result;

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
                    throw new IllegalArgumentException(
                            "Неизвестная геометрическая фигура."
                    );
            }

            area = result[0];
            perimeter = result[1];

            resultLabel.setText(String.format(
                    "<html>Площадь: %.4f<br>Периметр: %.4f</html>",
                    area,
                    perimeter
            ));
            
            calculationHistory.add(
                    selectedShape,
                    area,
                    perimeter
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Пожалуйста, введите действительные числовые значения.",
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Ошибка расчета",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private double readValue(JTextField field) {
        return InputUtils.parsePositiveDouble(field.getText());
    }
    
    private void showHistory() {

        if (calculationHistory.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "История вычислений пуста.",
                    "История",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                calculationHistory.getFormattedHistory(),
                "История вычислений",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void clearFields() {

        lengthField.setText("");
        widthField.setText("");

        sideField.setText("");
        radiusField.setText("");

        sideAField.setText("");
        sideBField.setText("");
        sideCField.setText("");

        resultLabel.setText(
                "Площадь: -    Периметр: -"
        );

        switch ((String) shapeComboBox.getSelectedItem()) {

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
