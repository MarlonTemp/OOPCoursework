import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class HorseRacingGUI {

    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new HorseRacingGUI());

    }

    public HorseRacingGUI() {
        JFrame frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        final int VERT_COLS = 5;
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel topGrid = new JPanel(new GridLayout(1,VERT_COLS));
        JPanel verticalFlow = new JPanel();
        topGrid.add(verticalFlow);
        JPanel bottomPanel = new JPanel(new BorderLayout());


        JButton button1 = new JButton("Button 1");
        button1.setLocation(100, 100);
        button1.setSize(100, 50);
        JButton button2 = new JButton("Button 2");
        button2.setLocation(200, 100);
        JButton button3 = new JButton("Button 3");
        Canvas canvas = new Canvas();


        canvas.setBackground(Color.GREEN);

        Vector[] track = new Vector[] {
            new Vector(0, 100),
            new Vector(100, 0),
            new Vector(200, 100),
            new Vector(300, 200),
            new Vector(400, 100),
            new Vector(300, 0),
            new Vector(200, 100),
            new Vector(100, 200),
        };

        //AI generated example of track
        Vector[] octagon = new Vector[] {
            new Vector(100 + 50, 100), // Right
            new Vector(100 + 35, 100 - 35), // Top-right
            new Vector(100, 100 - 50), // Top
            new Vector(100 - 35, 100 - 35), // Top-left
            new Vector(100 - 50, 100), // Left
            new Vector(100 - 35, 100 + 35), // Bottom-left
            new Vector(100, 100 + 50), // Bottom
            new Vector(100 + 35, 100 + 35) // Bottom-right
        };
        
        //Example tracks that a horse could follow
        canvas.addShapeReturn(track);

        canvas.addShapeReturn(Vector.addOffset((track), new Vector(0, 20)));
        canvas.addShapeReturn(octagon);

        frame.add(mainPanel);
        mainPanel.add(topGrid);
        mainPanel.add(bottomPanel);
        topGrid.add(button1);
        topGrid.add(button2);
        bottomPanel.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private int incrementTimer(int timer) {
        final int TIME_INCREMENT = 100;
        try {
            Thread.sleep(TIME_INCREMENT);
            return timer + TIME_INCREMENT;
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return timer;
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

class Vector {
    int x, y;
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector[] addOffset(Vector[] points, Vector v) {
        Vector[] newPoints = new Vector[points.length];
        for (int i = 0; i < points.length; i++) {
            newPoints[i] = new Vector(points[i].x + v.x, points[i].y + v.y);
        }
        return newPoints;
    }
}