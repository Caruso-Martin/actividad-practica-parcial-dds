package services.CurrencyConverter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import services.CurrencyConverter.entities.Exchange;

public interface CurrencyConverterService {
    @GET("convert")
    Call<Exchange> exchange(@Query("q") String currencies,
                            @Query("compact") String compact,
                            @Query("apiKey") String apiKey);
}