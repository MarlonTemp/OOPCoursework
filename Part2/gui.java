import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

class HorseRacingGUI {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SwingUtilities.invokeLater(() -> new HorseRacingGUI());

    }

    public HorseRacingGUI() {
        Horse winningHorse;

        JFrame frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        Horse[] availableHorses = new Horse[0];
        try {
            availableHorses = FileHandler.readHorses();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] horseNames = new String[availableHorses.length];
        for (int i = 0; i < availableHorses.length; i++) {
            horseNames[i] = availableHorses[i].getName();
            
        }

        System.out.println("Horse names: ");
        JComboBox<Horse> horseComboBox = new JComboBox<>();
        for (Horse s : availableHorses) {
            horseComboBox.addItem(s);
        }

        final int VERT_COLS = 2;
        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel topGrid = new JPanel(new GridLayout(1,VERT_COLS));
        JPanel flow1 = new JPanel(new FlowLayout());
        topGrid.add(flow1);
        JPanel flow2 = new JPanel(new FlowLayout());
        topGrid.add(flow2);
        JPanel bottomPanel = new JPanel(new BorderLayout());



        JButton button1 = new JButton("Add Horse");
        button1.addActionListener(e -> {
            Horse selectedHorse = (Horse) horseComboBox.getSelectedItem();
            if (selectedHorse != null) {
                HorseManager.appendHorse(selectedHorse);
            }
        });

        JButton button2 = new JButton("Begin Race");
        button2.addActionListener(e -> {
            Race r = new Race(10);
            winningHorse = r.startRace();

        });



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
        flow1.add(button1);
        flow1.add(horseComboBox);
        flow2.add(button2);
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