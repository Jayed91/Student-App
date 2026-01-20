package student.app;

public class Start {
    public static void main(String[] args) {
        // Use EventQueue for Swing thread safety
        javax.swing.SwingUtilities.invokeLater(() -> {
            new StudentManagerFrame();
        });
    }
}