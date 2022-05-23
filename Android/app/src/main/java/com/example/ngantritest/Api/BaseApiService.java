package com.example.ngantritest.Api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {
    //===Login & Register
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("username") String username,
                                       @Field("email") String email,
                                       @Field("password") String password);

    @GET("Banking/tampilTempat1.php")
    Call<ResponseBody> tampilTempatRequest();

    @FormUrlEncoded
    @POST("Banking/tampilPelayan1.php")
    Call<ResponseBody> ambilPelayanRequest1(@Field("username") String username);

    @FormUrlEncoded
    @POST("Banking/detailPelayan1.php")
    Call<ResponseBody> detailPelayanRequest1(@Field("namauser") String namauser,
                                             @Field("username") String username,
                                             @Field("type_loket") String type_loket);

    @FormUrlEncoded
    @POST("Banking/crudAmbil1.php")
    Call<ResponseBody> crudAmbilRequest1(@Field("namauser") String namauser,
                                         @Field("username") String username,
                                         @Field("type_loket") String type_loket);

    @FormUrlEncoded
    @POST("Banking/notif1.php")
    Call<ResponseBody> notifRequest1(@Field("namauser") String namauser,
                                     @Field("username") String username,
                                     @Field("type_loket") String type_loket);
}
