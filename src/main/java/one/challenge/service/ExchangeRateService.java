package one.challenge.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import one.challenge.modelos.Currency;
import one.challenge.api.CurrencyApi;

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
                        String.format("%.0f", resultadoConversion) + " " +
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
