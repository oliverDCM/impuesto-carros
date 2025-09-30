package impuestocarros;
import java.util.List;

public class ImpuestoController {

    private final ImpuestoModel modelo;
    private final ImpuestoView vista;

    public ImpuestoController(ImpuestoModel modelo, ImpuestoView vista) {
        this.modelo = modelo;
        this.vista = vista;

        iniciarControlador();
    }

    private void iniciarControlador() {
        cargarMarcas();

        vista.getComboMarca().addActionListener(e -> actualizarLineasPorMarca());
        vista.getComboLinea().addActionListener(e -> actualizarInfoVehiculo());
        vista.getBotonCalcular().addActionListener(e -> ejecutarCalculo());

        actualizarLineasPorMarca();
    }

    private void cargarMarcas() {
        List<String> marcas = modelo.getMarcas();
        vista.actualizarMarcas(marcas);
    }

    private void actualizarLineasPorMarca() {
        String marcaSeleccionada = (String) vista.getComboMarca().getSelectedItem();
        if (marcaSeleccionada != null) {
            List<String> lineas = modelo.getLineasPorMarca(marcaSeleccionada);
            vista.actualizarLineas(lineas);
        }
    }

    private void actualizarInfoVehiculo() {
        String marcaSeleccionada = (String) vista.getComboMarca().getSelectedItem();
        String lineaSeleccionada = (String) vista.getComboLinea().getSelectedItem();

        if (marcaSeleccionada != null && lineaSeleccionada != null) {
            ImpuestoModel.Vehiculo vehiculo = modelo.getVehiculo(marcaSeleccionada, lineaSeleccionada);
            vista.actualizarInfoVehiculo(vehiculo);
        }
    }

    private void ejecutarCalculo() {
        double valorComercial = vista.getValorComercialActual();
        if (valorComercial > 0) {
            var liquidacion = modelo.calcularImpuesto(
                    valorComercial,
                    vista.isProntoPago(),
                    vista.isServicioPublico(),
                    vista.isTrasladoCuenta()
            );
            vista.actualizarLiquidacion(liquidacion);
        }
    }
}