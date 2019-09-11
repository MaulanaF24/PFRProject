package com.catatankeuangan.service;

import com.catatankeuangan.utils.StringConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 1/10/2018.
 */

public class APIClient {
//local
//public static String BASE_URL ="http://192.168.43.120/edcmonitor/";
    //remote
   public static String BASE_URL ="http://192.168.5.237:8080/";
 //   public static String API_KEY ="59D3CFCA29DB8697C4962A36EEB653C8";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
/*
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("X-Api-Key", APIClient.API_KEY).build();
                return chain.proceed(newRequest);
            }
        };

*/
       // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        OkHttpClient client = new OkHttpClient.Builder().build();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.serializeNulls();
       // gb.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gb.create();

        //192.168.43.171
        //192.168.1.2

        retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .client(client)
                .build();



        return retrofit;
    }

}
