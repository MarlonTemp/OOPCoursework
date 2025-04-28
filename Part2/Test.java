import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test());
    }

    public Test() {
        JFrame frame = new JFrame("Hello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null); 
        
        frame.add(new Line(1, 1, 100, 100));
        
        frame.setVisible(true);
    }
}
