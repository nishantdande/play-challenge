package com.play.data.api

import com.play.data.model.LoginResponse
import com.play.data.model.Story
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTopStories(): Response<ArrayList<String>> = apiService.getTopStories()

    override suspend fun getTopStories(count: Int): Response<ArrayList<Story>> {
        TODO("Not yet implemented")
    }


    override suspend fun login(username: String, password: String): Response<LoginResponse>
            = apiService.login(username, password);
}