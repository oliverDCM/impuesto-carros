package impuestocarros;
public class ImpuestoController {

    private final ImpuestoModel modelo;
    private final ImpuestoView vista;

    public ImpuestoController(ImpuestoModel modelo, ImpuestoView vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.getBotonCalcular().addActionListener(e -> ejecutarCalculo());
    }

    private void ejecutarCalculo() {
        String marca = vista.getMarca();
        String linea = vista.getLinea();
        String anio = vista.getAnio();

        double valorComercial = modelo.buscarValorComercial(marca, linea, anio);

        var resultados = modelo.calcularImpuesto(
                valorComercial,
                vista.isProntoPago(),
                vista.isServicioPublico(),
                vista.isTrasladoCuenta()
        );

        vista.actualizarResultados(resultados);
    }
}