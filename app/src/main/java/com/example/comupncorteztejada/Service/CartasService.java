package com.example.comupncorteztejada.Service;

import com.example.comupncorteztejada.Entities.Cartas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CartasService {
    @GET("Cartas")
    Call<List<Cartas>> getAllCartas(@Query("limit") int limit, @Query("page") int page);

    @GET("Cartas/{id}")
    Call<Cartas> findCartas(@Path("id") int id);

    @POST("Cartas")
    Call<Cartas> create(@Body Cartas cartas);

    @PUT("Cartas/{id}")
    Call<Cartas> update(@Path("id") int id, @Body Cartas cartas);

    @DELETE("Cartas/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("image")
    Call<ImageResponse> subirImagen(@Body ImageToSave imagen);

    class ImageResponse {
        @SerializedName("url")
        private String url;

        public String getUrl(){
            return url;
        }
    }
    class ImageToSave {
        String base64Image;

        public ImageToSave(String base64Image){
            this.base64Image = base64Image;
        }
    }

}
