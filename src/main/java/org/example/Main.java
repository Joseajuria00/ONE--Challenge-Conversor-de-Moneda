package org.example;

import org.example.modelos.Currency;
import org.example.service.ExchangeRateService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Integer, Currency> currencies = ExchangeRateService.getAvailableCurrencies();
    static ExchangeRateService exchangeRateService = new ExchangeRateService();
    static List<String> conversionHistory = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Sea bienvenido/a al Conversor de Moneda");
        int opcion = 0;
        outerLoop:
        while (true) {
            System.out.println("\n*************************************************************************");
            System.out.println("1. Ver monedas disponibles.");
            System.out.println("2. Realizar conversion.");
            System.out.println("3 - Ver historial de conversiones.");
            System.out.println("4 - Salir.");
            System.out.println("*************************************************************************");
            System.out.println("Elija una opción válida:");
            try {
                opcion = lectura.nextInt();
                switch (opcion) {
                    case 1:
                        showAvailableCurrencies();
                        break;
                    case 2:
                        performConversion();
                        break;
                    case 3:
                        showConversionHistory(conversionHistory);
                        break;
                    case 4:
                        break outerLoop;
                }
            } catch (Exception e){
                System.err.println("ERROR: Opcion ingresada invalida");
            }
            lectura.nextLine();
            System.out.println("Presiona Enter para volver al menu...");
            lectura.nextLine();
        }
    }
    public static void showAvailableCurrencies(){
        System.out.println("\n---------- Divisas Disponibles ----------");
        currencies.forEach((key, currency) ->
                System.out.println(key + ". " + currency.getCode() + " - " + currency.getName())
        );
        System.out.println("-----------------------------------------");
    }
    public static void performConversion() throws Exception {
        System.out.println("\n---------- Conversion de Moneda ----------");
        Scanner lectura = new Scanner(System.in);
        Integer option1;
        Integer option2;
        Integer amount;
        Currency baseCurrency;
        Currency targetCurrency;
        showAvailableCurrencies();
        System.out.println("Elija la moneda a convertir:");
        option1 = lectura.nextInt();
        System.out.println("Elija la moneda conversora:");
        while(true){
            option2 = lectura.nextInt();
            if(option1!=option2){
                break;
            }
            System.out.println("No puedes elegir la misma moneda. Vuelve a elegir la moneda conversora:");
        }
        baseCurrency = currencies.get(option1);
        targetCurrency = currencies.get(option2);

        System.out.println("Ingrese el valor que desea convertir:");
        amount = lectura.nextInt();

        String resultado = exchangeRateService.getConversionResult(baseCurrency, targetCurrency, amount);
        System.out.println(resultado);

        String timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        conversionHistory.add(resultado + " (" + timeNow + ")");
        System.out.println("--------------------------------------------");
    }
    public static void showConversionHistory(List<String> conversionHistory){
        System.out.println("\n---------- Historial de Conversiones ----------");
        if (conversionHistory.isEmpty()) {
            System.out.println("No se han realizado conversiones.");
        } else {
            for (String item : conversionHistory) {
                System.out.println(item);
            }
        }
        System.out.println("-----------------------------------------------");
    }
}