package com.example.comupncorteztejada.Service;

import com.example.comupncorteztejada.Entities.Duelista;
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

public interface DuelistaService {
    @GET("Duelistas")
    Call<List<Duelista>> getAllUser(@Query("limit") int limit, @Query("page") int page);

    @GET("Duelistas/{id}")
    Call<Duelista> findUser(@Path("id") int id);

    @POST("Duelistas")
    Call<Duelista> create(@Body Duelista duelista);

    @PUT("Duelistas/{id}")
    Call<Duelista> update(@Path("id") int id, @Body Duelista duelista);

    @DELETE("Duelistas/{id}")
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
