package com.sjb.loginapi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("register.php")
    Call<registerResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("login.php")
    Call<loginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

}
