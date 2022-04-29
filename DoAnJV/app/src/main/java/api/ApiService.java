package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Model.thongtincanhiem.ThongTinCaNhiem;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://static.pipezero.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("covid/data.json")
    Call<ThongTinCaNhiem> getListThongTinCaNhiem();
}
