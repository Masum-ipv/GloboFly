package com.learning.globofly.services;

import android.os.Build;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String BASE_URL = "http://10.0.3.2:9000/";
    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("x-device-type", Build.DEVICE)
                    .addHeader("Accept-Language", Locale.getDefault().getLanguage())
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    };


    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logging);

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build());

    private static Retrofit retrofit = builder.build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
