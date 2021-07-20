package services.ExchangeRates;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.ExchangeRates.entities.Exchange;

import java.io.IOException;

public class ExchangeService {
    private static ExchangeService instancia = null;
    private static final String urlAPI = "http://api.exchangeratesapi.io/v1/";
    private static final String accessKey = "19a58e11ac9504508d1e30d601bab7cd";
    private Retrofit retrofit;

    public Double exchangeTo(Double ammount, String currency) throws IOException {
        Exchange exchange = this.exchange(currency);

        double rate;
        if(currency.equals("USD"))
            rate = Double.parseDouble(exchange.rates.USD);
        else
            rate = Double.parseDouble(exchange.rates.GBP);

        return ammount * rate;
    }

    public Exchange exchange(String cuerrencies) throws IOException {
        ExchangeRatesService exchangeRatesService = this.retrofit.create(ExchangeRatesService.class);
        Call<Exchange> requestExchange = exchangeRatesService.exchange(accessKey, cuerrencies);
        Response<Exchange> responseExchange = requestExchange.execute();
        return responseExchange.body();
    }

    /* Constructor */

    private ExchangeService() {
        this.retrofit = new Retrofit.Builder()
                                    .baseUrl(urlAPI)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    }

    /* Getters y Setters */

    public static ExchangeService getInstancia(){
        if(instancia == null){
            instancia = new ExchangeService();
        }
        return instancia;
    }
}
