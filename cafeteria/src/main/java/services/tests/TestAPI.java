package services.tests;

import services.CurrencyConverter.ExchangeService;
import services.CurrencyConverter.entites.Exchange;

import java.io.IOException;
import java.util.Scanner;

public class TestAPI { // Currency Converter
    public static void main(String[] args) throws IOException {
        ExchangeService exchangeService = ExchangeService.getInstancia();

        System.out.println("¡Bienvenido a Free Currency Converter!");
        System.out.println("- Todas las conversiones se relizan con el PESO ARGENTINO como moneda de base -\n");

        Scanner consola = new Scanner(System.in);
        System.out.println("Ingrese a que monto desea convertir: ");
        double montoAConvertir = Double.parseDouble(consola.nextLine());

        Exchange exchange = exchangeService.exchange("ARS_USD");

        System.out.println("\nUSD=" + Double.parseDouble(exchange.ARS_USD) * montoAConvertir + "\nARS=" + montoAConvertir);
    }
}
