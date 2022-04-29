package api;

import java.util.List;

import Model.entity.BenhNhanCustom;
import Model.entity.NV_Yte;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NhanVienYTeService {
    NhanVienYTeService nhanVienYTeService = new Retrofit.Builder()
            .baseUrl(BaseAPI.baseURL)
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(BaseAPI.gson))
            .build()
            .create(NhanVienYTeService.class);

    @GET("nvyte/getonebyuID")
    Call<NV_Yte> getOneNVYTeByUID(@Query("uID") String uID);
}
