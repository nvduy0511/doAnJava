package api;

import java.util.List;

import Model.entity.CoSoYTe;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface CoSoYTeService {
    CoSoYTeService CSYTService = new Retrofit.Builder()
            .baseUrl(BaseAPI.baseURL)
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(BaseAPI.gson))
            .build()
            .create(CoSoYTeService.class);

    @POST("cosoyte/add")
    Call<CoSoYTe> addCoSoYTe(@Body CoSoYTe coSoYTe);

    @GET("cosoyte/getone")
    Call<CoSoYTe> getOneCoSoYTe(@Query("macsyt") int macsyt);

    @PUT("cosoyte/updatecsyt")
    Call<Boolean> updateCoSoYTe(@Query("macsyt") int macsyt, @Body CoSoYTe coSoYTe);

    @GET("cosoyte/getall")
    Call<List<CoSoYTe>> getAllCoSoYTe();

    @DELETE("cosoyte/deletecsyt")
    Call<Boolean> deleteCoSoYTe(@Query("macsyt") int macsyt);

    @POST("cosoyte/undocsyt")
    Call<Boolean> UndoCoSoYTe(@Body CoSoYTe coSoYTe);
}
