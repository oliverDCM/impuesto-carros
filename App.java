package impuestocarros;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImpuestoModel modelo = new ImpuestoModel();
            ImpuestoView vista = new ImpuestoView();
            new ImpuestoController(modelo, vista);
            vista.setVisible(true);
        });
    }
}