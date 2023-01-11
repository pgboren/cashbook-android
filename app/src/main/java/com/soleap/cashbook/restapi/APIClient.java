package com.soleap.cashbook.restapi;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.content.AppPrefrences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
//    public static String BASE_URL = "http://167.172.77.123:8080";
    public static String BASE_URL = "http://192.168.0.104:8080";
    public static String API_URL = BASE_URL + "/api/";
    public static String STATIC_URL = BASE_URL + "/";
    private static Retrofit instance = null;

    public static Retrofit getClient() {
        if (instance == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    Request newRequest  = builder.addHeader("x-access-token", AppPrefrences.getUserAccessToken(Global.context)).build();
                    return chain.proceed(newRequest);
                }
            }).build();
            instance = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return instance;
    }
}









