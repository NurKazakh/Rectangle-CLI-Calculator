package ru.oivt.rectanglecli.ui.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class ShapePreviewPanel extends JPanel {

    private String shapeName = "Прямоугольник";
    private double[] parameters = {};
    private double currentScale = 1.0;

    public ShapePreviewPanel() {
        setPreferredSize(new Dimension(300, 220));
        setBackground(UIStyles.PANEL);
        setOpaque(false);
    }

    public void setShape(String shapeName) {
        this.shapeName = shapeName;
        repaint();
    }

    public void setParameters(double... parameters) {
        this.parameters = parameters;
        repaint();
    }
    
    /**
     *
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        // Фон Preview со скруглёнными углами
        g2.setColor(UIStyles.PANEL);
        g2.fillRoundRect(
                1, 1,
                getWidth() - 2,
                getHeight() - 2,
                20, 20
        );
        // Рамка Preview
        g2.setColor(UIStyles.BORDER);
        g2.drawRoundRect(
                1, 1,
                getWidth() - 2,
                getHeight() - 2,
                20, 20
        );
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE
        );
        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(new Color(
                UIStyles.PRIMARY.getRed(),
                UIStyles.PRIMARY.getGreen(),
                UIStyles.PRIMARY.getBlue(),
                70
        ));

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        // Расчёт масштаба относительно размеров панели
        double scaleX = (getWidth() - 20.0) / 260.0;
        double scaleY = (getHeight() - 20.0) / 220.0;
        double scale = Math.min(scaleX, scaleY);
        scale = Math.max(0.1, scale);

        AffineTransform originalTransform = g2.getTransform();

        // Масштабирование относительно центра панели
        g2.translate(
                centerX * (1 - scale),
                centerY * (1 - scale)
        );
        g2.scale(scale, scale);
        

        switch (shapeName) {

	        case "Прямоугольник":
	
	            double length = 180;
	            double width = 100;
	
	            // Получаем реальные размеры
	            if (parameters.length == 2) {
	                length = parameters[0];
	                width = parameters[1];
	            }
	
	            // Подбираем масштаб с сохранением пропорций
	            double shapeScale = Math.min(
	                    180.0 / length,
	                    120.0 / width
	            );
	            currentScale = shapeScale * scale;
	            
	            int rectWidth = (int) (length * shapeScale);
	            int rectHeight = (int) (width * shapeScale);
	
	            int rectX = centerX - rectWidth / 2;
	            int rectY = centerY - rectHeight / 2;
	
	            // Заливка
	            g2.setColor(new Color(
	                    UIStyles.PRIMARY.getRed(),
	                    UIStyles.PRIMARY.getGreen(),
	                    UIStyles.PRIMARY.getBlue(),
	                    70
	            ));
	
	            g2.fillRect(rectX, rectY, rectWidth, rectHeight);
	
	            // Контур
	            g2.setColor(UIStyles.PRIMARY);
	            g2.drawRect(rectX, rectY, rectWidth, rectHeight);
	            g2.setColor(UIStyles.TEXT);
	            g2.drawLine(
	                    rectX,
	                    rectY + rectHeight + 15,
	                    rectX + rectWidth,
	                    rectY + rectHeight + 15
	            );
	            g2.drawLine(
	                    rectX - 15,
	                    rectY,
	                    rectX - 15,
	                    rectY + rectHeight
	            );
	            // Подписи
	            g2.setFont(UIStyles.DEFAULT_FONT);
	            g2.drawString(
	                    "Длина",
	                    centerX - 20,
	                    rectY + rectHeight + 35
	            );
	            // Вертикальная надпись «Ширина»
	            g2.rotate(
	                    -Math.PI / 2,
	                    rectX - 30,
	                    centerY
	            );
	            g2.drawString(
	                    "Ширина",
	                    rectX - 30 - g2.getFontMetrics().stringWidth("Ширина") / 2,
	                    centerY
	            );
	            g2.rotate(
	                    Math.PI / 2,
	                    rectX - 30,
	                    centerY
	            );
	
	            break;
	            
	        case "Квадрат":
	        	double side = 100;

	        	if (parameters.length == 1) {
	        	    side = parameters[0];
	        	}

	        	currentScale = (120.0 / side) * scale;
	        	
	            g2.setColor(new Color(
	                    UIStyles.PRIMARY.getRed(),
	                    UIStyles.PRIMARY.getGreen(),
	                    UIStyles.PRIMARY.getBlue(),
	                    70
	            ));
	            g2.fillRect(centerX - 60, centerY - 60, 120, 120);
	            g2.setColor(UIStyles.PRIMARY);
	            g2.drawRect(centerX - 60, centerY - 60, 120, 120);
	            // Размерная линия стороны
	            g2.setColor(UIStyles.TEXT);
	            g2.drawLine(
	                    centerX - 60, centerY + 72,
	                    centerX + 60, centerY + 72
	            );
	            g2.setColor(UIStyles.TEXT);
	            g2.setFont(UIStyles.DEFAULT_FONT);
	            String sideText = "Сторона";
	            int sideTextX = centerX - g2.getFontMetrics().stringWidth(sideText) / 2;
	            g2.drawString(sideText, sideTextX, centerY + 100);
	            break;

	        case "Круг":
	        	double radius = 50;

	        	if (parameters.length == 1) {
	        	    radius = parameters[0];
	        	}

	        	currentScale = (60.0 / radius) * scale;
	        	
	            g2.setColor(new Color(
	                    UIStyles.PRIMARY.getRed(),
	                    UIStyles.PRIMARY.getGreen(),
	                    UIStyles.PRIMARY.getBlue(),
	                    70
	            ));
	            g2.fillOval(centerX - 60, centerY - 60, 120, 120);
	            g2.setColor(UIStyles.PRIMARY);
	            g2.drawOval(centerX - 60, centerY - 60, 120, 120);
	            g2.setColor(UIStyles.TEXT);
	            g2.setFont(UIStyles.DEFAULT_FONT);
	            // Радиус
	            g2.setColor(UIStyles.TEXT);
	            g2.drawLine(centerX, centerY, centerX + 60, centerY);
	            // Стрелка на конце радиуса
	            g2.drawLine(centerX + 60, centerY, centerX + 52, centerY - 4);
	            g2.drawLine(centerX + 60, centerY, centerX + 52, centerY + 4);	            g2.fillOval(centerX - 4, centerY - 4, 8, 8);
	            g2.drawString("R", centerX + 25, centerY - 10);

	            break;
	            
	        case "Треугольник":

	            double a = 100;
	            double b = 100;
	            double c = 100;

	            if (parameters.length == 3) {
	                a = parameters[0];
	                b = parameters[1];
	                c = parameters[2];
	            }
	            // Проверка существования треугольника
	            if (a + b <= c || a + c <= b || b + c <= a) {
	                break;
	            }
	            // Вычисляем координаты вершины
	            double vertexX = (b * b + c * c - a * a) / (2 * c);

	            double triangleHeight = Math.sqrt(
	                    b * b - vertexX * vertexX
	            );
	            // Масштабирование с сохранением пропорций
	            double triangleScale = Math.min(
	                    150.0 / c,
	                    120.0 / triangleHeight
	            );
	            currentScale = triangleScale * scale;
	            
	            double scaledC = c * triangleScale;
	            double scaledHeight = triangleHeight * triangleScale;
	            double scaledVertexX = vertexX * triangleScale;

	            // Координаты основания
	            int baseLeftX = (int) (centerX - scaledC / 2);
	            int baseRightX = (int) (centerX + scaledC / 2);
	            int baseY = (int) (centerY + scaledHeight / 2);

	            // Координаты вершины
	            int topX = (int) (baseLeftX + scaledVertexX);
	            int topY = (int) (baseY - scaledHeight);

	            int[] xPoints = {
	                    baseLeftX,
	                    baseRightX,
	                    topX
	            };

	            int[] yPoints = {
	                    baseY,
	                    baseY,
	                    topY
	            };

	            // Заливка
	            g2.setColor(new Color(
	                    UIStyles.PRIMARY.getRed(),
	                    UIStyles.PRIMARY.getGreen(),
	                    UIStyles.PRIMARY.getBlue(),
	                    70
	            ));

	            g2.fillPolygon(xPoints, yPoints, 3);

	            // Контур
	            g2.setColor(UIStyles.PRIMARY);
	            g2.drawPolygon(xPoints, yPoints, 3);

	            // Обозначения сторон
	            g2.setColor(UIStyles.TEXT);
	            g2.setFont(UIStyles.DEFAULT_FONT);

	            // Сторона a
	            g2.drawString(
	                    "a",
	                    (baseRightX + topX) / 2 + 8,
	                    (baseY + topY) / 2
	            );

	            // Сторона b
	            g2.drawString(
	                    "b",
	                    (baseLeftX + topX) / 2 - 15,
	                    (baseY + topY) / 2
	            );

	            // Сторона c
	            g2.drawString(
	                    "c",
	                    centerX - 5,
	                    baseY + 20
	            );

	            break;
	        

            default:
                break;
        }

        g2.setTransform(originalTransform);
        g2.setColor(UIStyles.TEXT);
        g2.setFont(UIStyles.DEFAULT_FONT);

        String scaleText;

        if (currentScale >= 1.0) {
            scaleText = String.format(
                    java.util.Locale.US,
                    "Масштаб: %.2f:1",
                    currentScale
            );
        } else {
            scaleText = String.format(
                    java.util.Locale.US,
                    "Масштаб: 1:%.2f",
                    1.0 / currentScale
            );
        }

        int scaleTextX = getWidth()
                - g2.getFontMetrics().stringWidth(scaleText) - 15;

        g2.drawString(
                scaleText,
                scaleTextX,
                getHeight() - 15
        );
        
        g2.dispose();
    }
}