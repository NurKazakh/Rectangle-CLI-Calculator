package ru.oivt.rectanglecli.ui.gui;

import ru.oivt.rectanglecli.model.Rectangle;
import ru.oivt.rectanglecli.model.Square;
import ru.oivt.rectanglecli.model.Circle;
import ru.oivt.rectanglecli.model.Triangle;

import java.util.ArrayList;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.CardLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

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
	
	private List<String> calculationHistory;
	
	private JLabel resultLabel;
	private JButton calculateButton;
	
    public MainWindow() {
    	calculationHistory = new ArrayList<>();

        setTitle("Geometric GUI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 500);
        setMinimumSize(new Dimension(650, 450));

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
        headerPanel.setBackground(UIStyles.BACKGROUND);
        headerPanel.setBorder(UIStyles.HEADER_BORDER);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.PAGE_START);

        initializeInputPanels();

        JPanel selectionPanel = new JPanel();
        selectionPanel.setBackground(UIStyles.BACKGROUND);

        JLabel shapeLabel = new JLabel("Выберите фигуру:");
        UIStyles.styleLabel(shapeLabel);

        shapeComboBox = new JComboBox<>(new String[] {
                "Прямоугольник",
                "Квадрат",
                "Круг",
                "Треугольник"
        });
        shapeComboBox.setFont(UIStyles.DEFAULT_FONT);

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

        lengthField = new JTextField(10);
        widthField = new JTextField(10);

        JLabel lengthLabel = new JLabel("Длина:");
        JLabel widthLabel = new JLabel("Ширина:");

        UIStyles.styleTextField(lengthField);
        UIStyles.styleTextField(widthField);
        UIStyles.styleLabel(lengthLabel);
        UIStyles.styleLabel(widthLabel);

        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(widthLabel);
        panel.add(widthField);

        return panel;
    }

    private JPanel createSquarePanel() {

        JPanel panel = new JPanel();

        sideField = new JTextField(10);
        
        JLabel sideLabel = new JLabel("Сторона:");
        
        UIStyles.styleTextField(sideField);
        UIStyles.styleLabel(sideLabel);

        panel.add(sideLabel);
        panel.add(sideField);

        return panel;
    }

    private JPanel createCirclePanel() {

        JPanel panel = new JPanel();

        radiusField = new JTextField(10);

        JLabel radiusLabel = new JLabel("Радиус:");
        
        UIStyles.styleTextField(radiusField);
        UIStyles.styleLabel(radiusLabel);

        panel.add(radiusLabel);
        panel.add(radiusField);

        return panel;
    }

    private JPanel createTrianglePanel() {

        JPanel panel = new JPanel();

        sideAField = new JTextField(5);
        sideBField = new JTextField(5);
        sideCField = new JTextField(5);

        JLabel sideALabel = new JLabel("Сторона A:");
        JLabel sideBLabel = new JLabel("Сторона B:");
        JLabel sideCLabel = new JLabel("Сторона C:");

        UIStyles.styleTextField(sideAField);
        UIStyles.styleTextField(sideBField);
        UIStyles.styleTextField(sideCField);
        UIStyles.styleLabel(sideALabel);
        UIStyles.styleLabel(sideBLabel);
        UIStyles.styleLabel(sideCLabel);

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

        panel.setBackground(UIStyles.BACKGROUND);

        resultLabel = new JLabel(
                "Площадь: -    Периметр: -"
        );
        calculateButton = new JButton("Рассчитать");
        JButton historyButton = new JButton("История");
        JButton clearButton = new JButton("Очистить");

        UIStyles.styleLabel(resultLabel);
        UIStyles.stylePrimaryButton(calculateButton);
        UIStyles.styleButton(historyButton);
        UIStyles.styleButton(clearButton);

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

            switch (selectedShape) {

                case "Прямоугольник":

                    Rectangle rectangle = new Rectangle(
                            readValue(lengthField),
                            readValue(widthField)
                    );

                    area = rectangle.area();
                    perimeter = rectangle.perimeter();

                    break;

                case "Квадрат":

                    Square square = new Square(
                            readValue(sideField)
                    );

                    area = square.area();
                    perimeter = square.perimeter();

                    break;

                case "Круг":

                    Circle circle = new Circle(
                            readValue(radiusField)
                    );

                    area = circle.area();
                    perimeter = circle.circumference();

                    break;

                case "Треугольник":

                    Triangle triangle = new Triangle(
                            readValue(sideAField),
                            readValue(sideBField),
                            readValue(sideCField)
                    );

                    area = triangle.area();
                    perimeter = triangle.perimeter();

                    break;

                default:
                    throw new IllegalArgumentException(
                            "Неизвестная геометрическая фигура."
                    );
            }

            resultLabel.setText(String.format(
                    "<html>Площадь: %.4f<br>Периметр: %.4f</html>",
                    area,
                    perimeter
            ));
            
            calculationHistory.add(String.format(
                    "%s | Площадь: %.4f | Периметр: %.4f",
                    selectedShape,
                    area,
                    perimeter
            ));

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

        String input = field.getText().trim();

        if (input.isEmpty()) {
            throw new NumberFormatException(
                    "Поле ввода пустое."
            );
        }

        double value = Double.parseDouble(
                input.replace(',', '.')
        );

        if (!Double.isFinite(value)) {
            throw new NumberFormatException(
                    "Необходимо ввести конечное число."
            );
        }

        return value;
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

        StringBuilder history = new StringBuilder();

        for (int i = 0; i < calculationHistory.size(); i++) {

            history.append(i + 1)
                   .append(". ")
                   .append(calculationHistory.get(i))
                   .append("\n\n");
        }

        JOptionPane.showMessageDialog(
                this,
                history.toString(),
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
