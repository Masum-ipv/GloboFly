package com.learning.globofly.network;

import com.learning.globofly.models.Destination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("destination")
    Call<List<Destination>> getDestinations();

    @GET("messages")
    Call<String> getMessage();

    @GET("destination/{id}")
    Call<Destination> getDestination(@Path("id") int id);

    @POST("destination")
    Call<Destination> addDestination(@Body Destination destination);

    @PUT("destination/{id}")
    Call<Destination> updateDestination(@Path("id") int id, @Body Destination destination);

    @DELETE("destination/{id}")
    Call<Destination> deleteDestination(@Path("id") int id);
}
