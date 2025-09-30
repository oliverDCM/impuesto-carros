package impuestocarros;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ImpuestoView extends JFrame {

    private JComboBox<String> comboMarca = new JComboBox<>();
    private JComboBox<String> comboLinea = new JComboBox<>();
    private JLabel etiquetaAnio = new JLabel();
    private JLabel etiquetaValorComercialBase = new JLabel();
    private JButton botonCalcular = new JButton("Calcular Impuesto");

    private JCheckBox checkProntoPago = new JCheckBox("Pronto Pago");
    private JCheckBox checkServicioPublico = new JCheckBox("Servicio Público");
    private JCheckBox checkTrasladoCuenta = new JCheckBox("Traslado de Cuenta");

    private JLabel etiquetaDescuentoProntoPago = new JLabel("Descuento Pronto Pago: $0");
    private JLabel etiquetaDescuentoServicioPublico = new JLabel("Descuento Servicio Público: $0");
    private JLabel etiquetaDescuentoTraslado = new JLabel("Descuento Traslado Cuenta: $0");
    private JLabel etiquetaTotalPagar = new JLabel("TOTAL A PAGAR: $0");

    private double valorComercialActual = 0;

    public ImpuestoView() {
        setTitle("Cálculo de Impuesto Vehicular");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelVehiculo = new JPanel(new GridLayout(5, 2, 10, 10));
        panelVehiculo.setBorder(BorderFactory.createTitledBorder("1. Seleccione el Vehículo"));
        panelVehiculo.add(new JLabel("Marca:"));
        panelVehiculo.add(comboMarca);
        panelVehiculo.add(new JLabel("Línea:"));
        panelVehiculo.add(comboLinea);
        panelVehiculo.add(new JLabel("Año (Modelo):"));
        panelVehiculo.add(etiquetaAnio);
        panelVehiculo.add(new JLabel("Valor Comercial:"));
        panelVehiculo.add(etiquetaValorComercialBase);
        panelVehiculo.add(new JLabel());

        JPanel panelDescuentos = new JPanel(new FlowLayout());
        panelDescuentos.setBorder(BorderFactory.createTitledBorder("2. Seleccione Descuentos"));
        panelDescuentos.add(checkProntoPago);
        panelDescuentos.add(checkServicioPublico);
        panelDescuentos.add(checkTrasladoCuenta);

        JPanel panelCalculo = new JPanel();
        panelCalculo.setBorder(BorderFactory.createTitledBorder("3. Calcule el Total"));
        panelCalculo.add(botonCalcular);

        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Liquidación Final"));
        etiquetaTotalPagar.setFont(new Font("Arial", Font.BOLD, 16));
        panelResultados.add(etiquetaDescuentoProntoPago);
        panelResultados.add(etiquetaDescuentoServicioPublico);
        panelResultados.add(etiquetaDescuentoTraslado);
        panelResultados.add(Box.createVerticalStrut(10));
        panelResultados.add(etiquetaTotalPagar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelVehiculo, BorderLayout.NORTH);
        panelSuperior.add(panelDescuentos, BorderLayout.CENTER);
        panelSuperior.add(panelCalculo, BorderLayout.SOUTH);

        Container panelPrincipal = getContentPane();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelResultados, BorderLayout.CENTER);
    }

    public void actualizarMarcas(List<String> marcas) {
        comboMarca.setModel(new DefaultComboBoxModel<>(marcas.toArray(new String[0])));
    }

    public void actualizarLineas(List<String> lineas) {
        comboLinea.setModel(new DefaultComboBoxModel<>(lineas.toArray(new String[0])));
    }

    public void actualizarInfoVehiculo(ImpuestoModel.Vehiculo vehiculo) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        if (vehiculo != null) {
            etiquetaAnio.setText(String.valueOf(vehiculo.anio));
            etiquetaValorComercialBase.setText(formatoMoneda.format(vehiculo.valorComercial));
            this.valorComercialActual = vehiculo.valorComercial;
        } else {
            etiquetaAnio.setText("");
            etiquetaValorComercialBase.setText("");
            this.valorComercialActual = 0;
        }
        limpiarResultados();
    }

    public void actualizarLiquidacion(Map<String, Double> liquidacion) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        etiquetaDescuentoProntoPago.setText("Descuento Pronto Pago: " + formatoMoneda.format(liquidacion.get("Descuento Pronto Pago")));
        etiquetaDescuentoServicioPublico.setText("Descuento Servicio Público: " + formatoMoneda.format(liquidacion.get("Descuento Servicio Público")));
        etiquetaDescuentoTraslado.setText("Descuento Traslado Cuenta: " + formatoMoneda.format(liquidacion.get("Descuento Traslado Cuenta")));
        etiquetaTotalPagar.setText("TOTAL A PAGAR: " + formatoMoneda.format(liquidacion.get("Total a Pagar")));
    }

    public void limpiarResultados() {
        etiquetaDescuentoProntoPago.setText("Descuento Pronto Pago: $0");
        etiquetaDescuentoServicioPublico.setText("Descuento Servicio Público: $0");
        etiquetaDescuentoTraslado.setText("Descuento Traslado Cuenta: $0");
        etiquetaTotalPagar.setText("TOTAL A PAGAR: $0");
    }

    public JComboBox<String> getComboMarca() { return comboMarca; }
    public JComboBox<String> getComboLinea() { return comboLinea; }
    public JButton getBotonCalcular() { return botonCalcular; }
    public double getValorComercialActual() { return valorComercialActual; }
    public boolean isProntoPago() { return checkProntoPago.isSelected(); }
    public boolean isServicioPublico() { return checkServicioPublico.isSelected(); }
    public boolean isTrasladoCuenta() { return checkTrasladoCuenta.isSelected(); }
}