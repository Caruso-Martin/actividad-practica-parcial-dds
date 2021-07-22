package services.tests;

import services.ExchangeRates.ExchangeService;
import services.ExchangeRates.entities.Exchange;

import java.io.IOException;
import java.util.Scanner;

public class TestAPI2 { // ExchangeRates
    public static void main(String[] args) throws IOException {
        ExchangeService exchangeService = ExchangeService.getInstancia();

        System.out.println("¡Bienvenido a ExchangeRates Converter!");
        System.out.println("- Todas las conversiones se relizan con el EURO como moneda de base -\n");

        Scanner consola = new Scanner(System.in);
        System.out.println("Ingrese a que monto desea convertir: ");
        double montoAConvertir = Double.parseDouble(consola.nextLine());

        Exchange exchange = exchangeService.exchange("USD");

        System.out.println("\nUSD=" + Double.parseDouble(exchange.rates.USD) * montoAConvertir + "\nEUR=" + montoAConvertir);
    }
}
