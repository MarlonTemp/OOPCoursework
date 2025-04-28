
import java.awt.*;
import javax.swing.*;

class HorseRacingGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HorseRacingGUI());
    }

    public HorseRacingGUI() {
        JFrame frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton button1 = new JButton("Button 1");
        button1.setLocation(100, 100);
        button1.setSize(100, 50);
        JButton button2 = new JButton("Button 2");
        button2.setLocation(200, 100);
        JButton button3 = new JButton("Button 3");
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.GREEN);
        canvas.drawShapeReturn(new Vector[] {
            new Vector(0, 0),
            new Vector(200, 200),
            new Vector(300, 100),
            new Vector(400, 200)
        });

        frame.add(mainPanel);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        topPanel.add(button1);
        topPanel.add(button2);
        bottomPanel.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}


class Line extends JComponent {
    int x1, y1, x2, y2;
    public Line(int x1, int y1, int x2, int y2) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        System.out.println("Test");
    }

    public Line(Vector start, Vector end) {
        this.x1 = start.x;
        this.y1 = start.y;
        this.x2 = end.x;
        this.y2 = end.y;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
        System.out.println("Hello");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Math.abs(x2 - x1), Math.abs(y2 - y1));
    }
}

class Canvas extends JPanel {
    private Vector[] points;

    public Canvas() {
        super();
    }

    public void drawShape(Vector[] points) {
        this.points = points;
        repaint(); //Redraws the shape with the added points
    }

    public void drawShapeReturn(Vector[] points) { //adds additional point which is same as start, so the shape is joined 
        this.points = new Vector[points.length + 1];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
        this.points[points.length] = points[0]; //connects the first and last point
        repaint();
    }
        

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (points != null) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < points.length - 1; i++) {
                g.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
            }
        }
    }
}

class Vector {
    int x, y;
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }
}