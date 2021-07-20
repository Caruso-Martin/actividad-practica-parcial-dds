package domain;

import services.ExchangeRates.ExchangeService;
import services.ExchangeRates.entities.Exchange;

import java.io.IOException;

public class TestAPI2 {
    public static void main(String[] args) throws IOException {
        ExchangeService exchangeService = ExchangeService.getInstancia();

        Exchange exchange = exchangeService.exchange("USD");

        System.out.println(exchange.rates.USD);
    }
}
