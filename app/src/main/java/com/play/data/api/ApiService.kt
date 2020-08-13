package com.play.data.api

import com.play.data.model.LoginResponse
import com.play.data.model.Story
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v0/topstories.json")
    suspend fun getTopStories(): Response<ArrayList<String>>

    @GET("v0/item/{id}.json")
    suspend fun getTopStories(@Path(value = "id") id:String) : Response<Story>

    @POST("login")
    suspend fun login(@Query("username") username: String ,
                      @Query("password") password: String  ): Response<LoginResponse>

}