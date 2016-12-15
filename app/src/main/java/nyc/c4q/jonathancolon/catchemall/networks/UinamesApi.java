package nyc.c4q.jonathancolon.catchemall.networks;

import nyc.c4q.jonathancolon.catchemall.UinamesModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dannylui on 12/14/16.
 */

public interface UinamesApi {
    @GET("api")
    Call<UinamesModel> getRandomName(
            @Query("amount") String amount,
            @Query("gender") String gender,
            @Query("region") String region
    );
}
