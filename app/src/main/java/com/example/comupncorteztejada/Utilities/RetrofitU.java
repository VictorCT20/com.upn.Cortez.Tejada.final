package com.example.comupncorteztejada.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitU {
    public static Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl("https://63874b59e399d2e473fac979.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
