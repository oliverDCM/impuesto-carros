package impuestocarros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImpuestoModel {

    public static class Vehiculo {
        String marca;
        String linea;
        int anio;
        double valorComercial;

        public Vehiculo(String marca, String linea, int anio, double valorComercial) {
            this.marca = marca;
            this.linea = linea;
            this.anio = anio;
            this.valorComercial = valorComercial;
        }
    }

    private final List<Vehiculo> vehiculos;
    private final double PORCENTAJE_PRONTO_PAGO = 0.07;
    private final double VALOR_FIJO_SERVICIO_PUBLICO = 40000.0;
    private final double PORCENTAJE_TRASLADO_CUENTA = 0.12;

    public ImpuestoModel() {
        vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo("Ford", "Fiesta", 2019, 45000000));
        vehiculos.add(new Vehiculo("Ford", "Explorer", 2020, 150000000));
        vehiculos.add(new Vehiculo("Chevrolet", "Spark GT", 2021, 48000000));
        vehiculos.add(new Vehiculo("Chevrolet", "Onix", 2022, 62000000));
        vehiculos.add(new Vehiculo("Renault", "Duster", 2023, 85000000));
        vehiculos.add(new Vehiculo("Renault", "Kwid", 2022, 49000000));
        vehiculos.add(new Vehiculo("Mazda", "3", 2021, 90000000));
        vehiculos.add(new Vehiculo("Mazda", "CX-5", 2022, 130000000));
        vehiculos.add(new Vehiculo("Kia", "Picanto", 2023, 55000000));
        vehiculos.add(new Vehiculo("Kia", "Sportage", 2020, 110000000));
    }

    public List<String> getMarcas() {
        return vehiculos.stream()
                .map(v -> v.marca)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> getLineasPorMarca(String marca) {
        return vehiculos.stream()
                .filter(v -> v.marca.equalsIgnoreCase(marca))
                .map(v -> v.linea)
                .sorted()
                .collect(Collectors.toList());
    }

    public Vehiculo getVehiculo(String marca, String linea) {
        return vehiculos.stream()
                .filter(v -> v.marca.equalsIgnoreCase(marca) && v.linea.equalsIgnoreCase(linea))
                .findFirst()
                .orElse(null);
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