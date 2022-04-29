package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import Model.entity.ConNguoi;
import Model.entity.NguoiDung;
import retrofit2.Call;
import Model.entity.BenhNhanCustom;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface BenhNhanService {

    BenhNhanService benhNhanService = new Retrofit.Builder()
            .baseUrl(BaseAPI.baseURL)
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(BaseAPI.gson))
            .build()
            .create(BenhNhanService.class);

    @POST("benhnhan/add")
    Call<BenhNhanCustom> addBenhNhan(@Body BenhNhanCustom benhNhanCustom, @Query("maCoSoYTe") int maCoSoYTe);

    @GET("benhnhan/getallbenhnhan")
    Call<List<BenhNhanCustom>> getAllBenhNhan();

    @PUT("benhnhan/updatebenhnhan")
    Call<Boolean> updateBenhNhan(@Query("maBN") int maBN, @Body BenhNhanCustom benhNhanCustom);

    @DELETE("benhnhan/deletebenhnhan")
    Call<Boolean> deleteBenhNhan(@Query("maBN") int maBN);

    @POST("benhnhan/undobenhnhan")
    Call<Boolean> UndoBenhNhan(@Body BenhNhanCustom benhNhanCustom);
}
