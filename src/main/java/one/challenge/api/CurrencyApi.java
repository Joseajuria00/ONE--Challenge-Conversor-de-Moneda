package one.challenge.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyApi {
    private static final String API_KEY = "a1f1df931675920e0a6b59a2";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/";
    public CurrencyApi() {}
    public String sendGetRequest(String endpoint) throws Exception {
        URI url = URI.create(BASE_URL + endpoint);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new Exception("Error al realizar la solicitud HTTP: " + response.statusCode());
        }
    }
}
