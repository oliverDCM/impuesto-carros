package impuestocarros;
import java.util.HashMap;
import java.util.Map;

public class ImpuestoModel {

    private Map<String, Double> baseDatosComercial;
    private final double PORCENTAJE_PRONTO_PAGO = 0.07; // 7%
    private final double VALOR_FIJO_SERVICIO_PUBLICO = 40000.0;
    private final double PORCENTAJE_TRASLADO_CUENTA = 0.12; // 12%

    public ImpuestoModel() {
        baseDatosComercial = new HashMap<>();
        baseDatosComercial.put("FORD-TAURUS-2018", 95000000.0);
        baseDatosComercial.put("CHEVROLET-SPARK-2020", 42000000.0);
        baseDatosComercial.put("RENAULT-DUSTER-2022", 78000000.0);
    }

    public double buscarValorComercial(String marca, String linea, String anio) {
        String clave = (marca + "-" + linea + "-" + anio).toUpperCase();
        return baseDatosComercial.getOrDefault(clave, 0.0);
    }

    public Map<String, Double> calcularImpuesto(double valorComercial, boolean aplicaProntoPago, boolean aplicaServicioPublico, boolean aplicaTraslado) {
        Map<String, Double> resultado = new HashMap<>();
        double valorActual = valorComercial;

        resultado.put("Valor Comercial", valorComercial);
        resultado.put("Descuento Pronto Pago", 0.0);
        resultado.put("Descuento Servicio Público", 0.0);
        resultado.put("Descuento Traslado Cuenta", 0.0);

        if (aplicaProntoPago) {
            double descuento = valorActual * PORCENTAJE_PRONTO_PAGO;
            resultado.put("Descuento Pronto Pago", descuento);
            valorActual -= descuento;
        }

        if (aplicaServicioPublico) {
            double descuento = VALOR_FIJO_SERVICIO_PUBLICO;
            resultado.put("Descuento Servicio Público", descuento);
            valorActual -= descuento;
        }

        if (aplicaTraslado) {
            double descuento = valorActual * PORCENTAJE_TRASLADO_CUENTA;
            resultado.put("Descuento Traslado Cuenta", descuento);
            valorActual -= descuento;
        }

        resultado.put("Total a Pagar", valorActual);

        return resultado;
    }
}