package domain.caja.cotizacion;

import java.io.IOException;

public interface CotizarStrategy {
    Double cotizar(Double monto) throws IOException;
}
