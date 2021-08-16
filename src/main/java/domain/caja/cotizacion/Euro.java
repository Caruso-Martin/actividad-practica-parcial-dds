package domain.caja.cotizacion;

import services.CurrencyConverter.ExchangeService;
import services.CurrencyConverter.entities.Exchange;

import java.io.IOException;

public class Euro implements CotizarStrategy {
    @Override
    public Double cotizar(Double monto) throws IOException {
        ExchangeService exchangeService = ExchangeService.getInstancia();
        Exchange exchange = exchangeService.exchange("ARS_EUR");

        return monto * Double.parseDouble(exchange.ARS_EUR);
    }

    @Override
    public String toString() {
        return "EUROS";
    }
}
