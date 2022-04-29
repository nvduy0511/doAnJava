package com.example.covidapp.api;

import com.example.covidapp.model.entity.ConNguoi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ConNguoiService {

    ConNguoiService conNguoiService = new Retrofit.Builder()
            .baseUrl(BaseAPI.baseURL)
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(BaseAPI.gson))
            .build()
            .create(ConNguoiService.class);

    @GET("connguoi/getone")
    Call<ConNguoi> getOneConNguoi(@Query("cmnd") String cmnd);

    @GET("connguoi/getonebyuid")
    Call<ConNguoi> getOneConNguoiByUID(@Query("uID") String uID);

    @GET("connguoi/getconnguoibyuid")
    Call<ConNguoi> getConNguoiByUID(@Query("uID") String uID);

    @POST("connguoi/add")
    Call<Boolean> addConNguoi(@Body ConNguoi conNguoi);

    @PUT("connguoi/update")
    Call<Boolean> updateConNguoi(@Query("cmnd") String cmnd,@Body ConNguoi conNguoi);
}
