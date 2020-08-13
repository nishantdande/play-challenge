package com.play.data.api

import com.play.data.model.LoginResponse
import com.play.data.model.Story
import retrofit2.Response
import java.util.*

interface ApiHelper {

    suspend fun getTopStories(): Response<ArrayList<String>>

    suspend fun getTopStories(count: Int): Response<ArrayList<Story>>

    suspend fun login(username: String, password: String): Response<LoginResponse>
}