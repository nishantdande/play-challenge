package com.play.data.api

import com.play.data.model.LoginResponse
import com.play.data.model.Story
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import java.util.*

interface ApiHelper {

    suspend fun getTopStories(): Call<ArrayList<String?>?>

    suspend fun getTopStories(count: Int): Observable<ArrayList<Story>>

    suspend fun login(username: String, password: String): Response<LoginResponse>
}