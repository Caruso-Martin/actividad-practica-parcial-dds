package services.ExchangeRates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import services.ExchangeRates.entities.Exchange;

public interface ExchangeRatesService {
    @GET("latest")
    Call<Exchange> exchange(@Query("access_key") String accessKey, @Query("symbols") String symbols);
}
