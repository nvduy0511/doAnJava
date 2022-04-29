package com.example.covidapp.api;

import com.example.covidapp.model.entity.BenhNhanCustom;
import com.example.covidapp.model.entity.ConNguoi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BenhNhanService {

    BenhNhanService benhNhanService = new Retrofit.Builder()
            .baseUrl(BaseAPI.baseURL)
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(BaseAPI.gson))
            .build()
            .create(BenhNhanService.class);

    @GET("benhnhan/getdiachibenhnhan")
    Call<List<BenhNhanCustom>> getBenhNhanCustom();

    @GET("benhnhan/getbenhnhancustomtheongay")
    Call<List<BenhNhanCustom>> getBenhNhanCustomTheoNgay(@Query("date") String date);
}
