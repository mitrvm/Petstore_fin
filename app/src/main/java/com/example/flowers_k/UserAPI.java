package com.example.flowers_k;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface UserAPI {
//    @GET("pet/{id}")
//    Call<Pet> getData(@Path("id") String id);

    @POST("user")
    Call<User> createUser(@Body User user);

    @GET("user/login?username={username}&password={password}")
    Call<User> createUser(@Path("username") String username, @Path("password") String password);

//    @DELETE("pet")
//    Call<Pet> deletePet(@Body Pet pet);

//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://petstore.swagger.io/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
}
