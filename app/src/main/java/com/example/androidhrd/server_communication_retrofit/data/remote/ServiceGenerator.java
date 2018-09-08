package com.example.androidhrd.server_communication_retrofit.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final  String BASE_URL="http://110.74.194.125:15000";
    private static Retrofit.Builder builder =new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static  <S> S createService(Class<S> serviceClass){
        return builder.
                build().create(serviceClass);
    }

}
