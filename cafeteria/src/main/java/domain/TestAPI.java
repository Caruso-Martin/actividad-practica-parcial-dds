package domain;

import services.CurrencyConverter.ExchangeService;
import services.CurrencyConverter.entites.Exchange;

import java.io.IOException;
import java.util.Scanner;

public class TestAPI {
    public static void main(String[] args) throws IOException {
        ExchangeService exchangeService = ExchangeService.getInstancia();

        Scanner consola = new Scanner(System.in);
        System.out.println("Ingrese a que monto desea convertir: ");
        double montoAConvertir = Double.parseDouble(consola.nextLine());

        Exchange exchange = exchangeService.exchange("ARS_USD");

        System.out.println("\nUSD=" + Double.parseDouble(exchange.ARS_USD) * montoAConvertir + "\nARS=" + montoAConvertir);
    }
}
