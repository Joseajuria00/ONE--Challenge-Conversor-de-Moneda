package org.example.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.api.CurrencyApi;
import org.example.modelos.Currency;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateService {
    private CurrencyApi currencyApi = new CurrencyApi();
    private Gson gson = new Gson();;
    public ExchangeRateService() {}
    public String getConversionResult(Currency baseCurrency, Currency targetCurrency, Integer amount) throws Exception {
        JsonObject jsonObject = getConversionData(baseCurrency, targetCurrency, amount);
        Double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();
        String result =
                amount.toString() + " " + jsonObject.get("base_code").toString().replace("\"", "") +" --> "+
                        resultadoConversion + " " +
                        jsonObject.get("target_code").toString().replace("\"", "");
        return result;
    }
    public JsonObject getConversionData(Currency baseCurrency, Currency targetCurrency, int amount) throws Exception {
        String endpoint = "pair/" + baseCurrency.getCode() + "/" + targetCurrency.getCode() + "/" + amount;
        String jsonResponse = currencyApi.sendGetRequest(endpoint);
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return jsonObject;
    }
    public static Map<Integer, Currency> getAvailableCurrencies() {
        /*List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("USD", "United States Dollar"));
        currencies.add(new Currency("EUR", "Euro"));
        currencies.add(new Currency("ARS", "Argentine Peso"));
        currencies.add(new Currency("BOB", "Bolivian Boliviano"));
        currencies.add(new Currency("BRL", "Brazilian Real"));
        currencies.add(new Currency("CLP", "Chilean Peso"));
        currencies.add(new Currency("COP", "Colombian Peso"));
        currencies.add(new Currency("CRC", "Costa Rican Colón"));
        currencies.add(new Currency("CUP", "Cuban Peso"));
        currencies.add(new Currency("DOP", "Dominican Peso"));
        currencies.add(new Currency("GTQ", "Guatemalan Quetzal"));
        currencies.add(new Currency("HNL", "Honduran Lempira"));
        currencies.add(new Currency("MXN", "Mexican Peso"));
        currencies.add(new Currency("NIO", "Nicaraguan Córdoba"));
        currencies.add(new Currency("PAB", "Panamanian Balboa"));
        currencies.add(new Currency("PYG", "Paraguayan Guarani"));
        currencies.add(new Currency("PEN", "Peruvian Sol"));
        currencies.add(new Currency("UYU", "Uruguayan Peso"));
        currencies.add(new Currency("VES", "Venezuelan Bolívar"));*/

        Map<Integer, Currency> currencies = new HashMap<>();
        currencies.put(1, new Currency("USD", "United States Dollar"));
        currencies.put(2, new Currency("EUR", "Euro"));
        currencies.put(3, new Currency("ARS", "Argentine Peso"));
        currencies.put(4, new Currency("BOB", "Bolivian Boliviano"));
        currencies.put(5, new Currency("BRL", "Brazilian Real"));
        currencies.put(6, new Currency("CLP", "Chilean Peso"));
        currencies.put(7, new Currency("COP", "Colombian Peso"));
        currencies.put(8, new Currency("CRC", "Costa Rican Colón"));
        currencies.put(9, new Currency("CUP", "Cuban Peso"));
        currencies.put(10, new Currency("DOP", "Dominican Peso"));
        currencies.put(11, new Currency("GTQ", "Guatemalan Quetzal"));
        currencies.put(12, new Currency("HNL", "Honduran Lempira"));
        currencies.put(13, new Currency("MXN", "Mexican Peso"));
        currencies.put(14, new Currency("NIO", "Nicaraguan Córdoba"));
        currencies.put(15, new Currency("PAB", "Panamanian Balboa"));
        currencies.put(16, new Currency("PYG", "Paraguayan Guarani"));
        currencies.put(17, new Currency("PEN", "Peruvian Sol"));
        currencies.put(18, new Currency("UYU", "Uruguayan Peso"));
        currencies.put(19, new Currency("VES", "Venezuelan Bolívar"));
        return currencies;
    }
}