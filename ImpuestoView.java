package impuestocarros;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class ImpuestoView extends JFrame {

    private JTextField campoMarca = new JTextField("FORD");
    private JTextField campoLinea = new JTextField("TAURUS");
    private JTextField campoAnio = new JTextField("2018");
    private JButton botonCalcular = new JButton("Calcular Impuesto");

    private JCheckBox checkProntoPago = new JCheckBox("Pronto Pago");
    private JCheckBox checkServicioPublico = new JCheckBox("Servicio Público");
    private JCheckBox checkTrasladoCuenta = new JCheckBox("Traslado de Cuenta");

    private JLabel etiquetaValorComercial = new JLabel("Valor Comercial: $0");
    private JLabel etiquetaDescuentoProntoPago = new JLabel("Descuento Pronto Pago: $0");
    private JLabel etiquetaDescuentoServicioPublico = new JLabel("Descuento Servicio Público: $0");
    private JLabel etiquetaDescuentoTraslado = new JLabel("Descuento Traslado Cuenta: $0");
    private JLabel etiquetaTotalPagar = new JLabel("TOTAL A PAGAR: $0");

    public ImpuestoView() {
        setTitle("Cálculo de Impuesto Vehicular");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos del Vehículo"));
        panelEntrada.add(new JLabel("Marca:"));
        panelEntrada.add(campoMarca);
        panelEntrada.add(new JLabel("Línea:"));
        panelEntrada.add(campoLinea);
        panelEntrada.add(new JLabel("Año (Modelo):"));
        panelEntrada.add(campoAnio);
        panelEntrada.add(new JLabel());
        panelEntrada.add(botonCalcular);

        JPanel panelDescuentos = new JPanel(new FlowLayout());
        panelDescuentos.setBorder(BorderFactory.createTitledBorder("Descuentos Aplicables"));
        panelDescuentos.add(checkProntoPago);
        panelDescuentos.add(checkServicioPublico);
        panelDescuentos.add(checkTrasladoCuenta);

        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Liquidación"));
        etiquetaTotalPagar.setFont(new Font("Arial", Font.BOLD, 16));
        panelResultados.add(etiquetaValorComercial);
        panelResultados.add(etiquetaDescuentoProntoPago);
        panelResultados.add(etiquetaDescuentoServicioPublico);
        panelResultados.add(etiquetaDescuentoTraslado);
        panelResultados.add(Box.createVerticalStrut(10));
        panelResultados.add(etiquetaTotalPagar);

        Container panelPrincipal = getContentPane();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);
        panelPrincipal.add(panelDescuentos, BorderLayout.CENTER);
        panelPrincipal.add(panelResultados, BorderLayout.SOUTH);
    }

    public void actualizarResultados(Map<String, Double> resultados) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        etiquetaValorComercial.setText("Valor Comercial: " + formatoMoneda.format(resultados.get("Valor Comercial")));
        etiquetaDescuentoProntoPago.setText("Descuento Pronto Pago: " + formatoMoneda.format(resultados.get("Descuento Pronto Pago")));
        etiquetaDescuentoServicioPublico.setText("Descuento Servicio Público: " + formatoMoneda.format(resultados.get("Descuento Servicio Público")));
        etiquetaDescuentoTraslado.setText("Descuento Traslado Cuenta: " + formatoMoneda.format(resultados.get("Descuento Traslado Cuenta")));
        etiquetaTotalPagar.setText("TOTAL A PAGAR: " + formatoMoneda.format(resultados.get("Total a Pagar")));
    }

    public String getMarca() { return campoMarca.getText(); }
    public String getLinea() { return campoLinea.getText(); }
    public String getAnio() { return campoAnio.getText(); }
    public boolean isProntoPago() { return checkProntoPago.isSelected(); }
    public boolean isServicioPublico() { return checkServicioPublico.isSelected(); }
    public boolean isTrasladoCuenta() { return checkTrasladoCuenta.isSelected(); }
    public JButton getBotonCalcular() { return botonCalcular; }
}