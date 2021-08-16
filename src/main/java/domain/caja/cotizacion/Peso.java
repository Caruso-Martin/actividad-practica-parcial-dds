package domain.caja.cotizacion;

public class Peso implements CotizarStrategy {
    @Override
    public Double cotizar(Double monto) {
        return monto;
    }

    @Override
    public String toString() {
        return "PESOS";
    }
}
