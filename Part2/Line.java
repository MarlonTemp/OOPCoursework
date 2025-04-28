import java.awt.*;
import javax.swing.JComponent;

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