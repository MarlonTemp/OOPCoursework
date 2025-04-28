import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class Canvas extends JPanel {
    private ArrayList<Vector[]> shapes = new ArrayList<>();

    public Canvas() {
        super();
    }

    public void addNewShape(Vector[] points) {
        shapes.add(points);
        repaint();
    }

    public void addShapeReturn(Vector[] points) {
        Vector[] temp = new Vector[points.length + 1];
        for (int i = 0; i < points.length; i++) {
            temp[i] = points[i];
        }
        temp[points.length] = points[0]; //connects the first and last point
        shapes.add(temp);
        repaint();
    }

    public void clearShapes() {
        shapes = new ArrayList<>();
    }
        

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (Vector[] shape : shapes) {
            for (int i = 0; i < shape.length - 1; i++) {
                g.drawLine(shape[i].x, shape[i].y, shape[i + 1].x, shape[i + 1].y);
            }
        }
    }
}