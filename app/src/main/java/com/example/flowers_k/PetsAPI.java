package com.example.flowers_k;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface PetsAPI {
    @GET("pet/{id}")
    Call<Pet> getData(@Path("id") String id);

    @POST("pet")
    Call<Pet> createPet(@Body Pet pet);

    @DELETE("pet/{id}")
    Call<Pet> deletePet(@Path("id") String id);

//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://petstore.swagger.io/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
}
