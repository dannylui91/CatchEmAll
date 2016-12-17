package nyc.c4q.jonathancolon.catchemall.networks;

import nyc.c4q.jonathancolon.catchemall.models.UinamesModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dannylui on 12/14/16.
 */

public class UinamesClient {
    private static final String API_URL = "http://uinames.com/";
    private static UinamesClient instance;
    private final UinamesApi api;

    public static synchronized UinamesClient getInstance() {
        if (instance == null) {
            instance = new UinamesClient();
        }
        return instance;
    }

    private UinamesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(UinamesApi.class);
    }

    public Call<UinamesModel> getRandomName() {
        return api.getRandomName("1", "male", "united states");
    }


}
