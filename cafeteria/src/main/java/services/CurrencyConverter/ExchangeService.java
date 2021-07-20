package services.CurrencyConverter;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.CurrencyConverter.entites.Exchange;

import java.io.IOException;

public final class ExchangeService {
    private static ExchangeService instancia = null;
    private static final String urlAPI = "https://free.currconv.com/api/v7/";
    private static final String apiKey = "da35d7817ffaad7e434a";
    private static final String compactConfiguration = "ultra";
    private Retrofit retrofit;

    public Exchange exchange(String currencies) throws IOException {
        CurrencyConverterService currencyConverterService = this.retrofit.create(CurrencyConverterService.class);
        Call<Exchange> requestExchange = currencyConverterService.exchange(currencies, compactConfiguration, apiKey);
        Response<Exchange> responseExchange = requestExchange.execute();
        return  responseExchange.body();
    }

    /* Constructor */

    public ExchangeService() {
        this.retrofit = new Retrofit.Builder()
                                    .baseUrl(urlAPI)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    }

    /* Getters y Setters */

    public static ExchangeService getInstancia() {
        if (instancia == null) {
            instancia = new ExchangeService();
        }
        return instancia;
    }
}
