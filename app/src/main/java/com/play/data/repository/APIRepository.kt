package com.play.data.repository

import com.play.data.api.ApiHelper
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getTopStories() =  apiHelper.getTopStories()

    suspend fun getTopStories(count: Int) =  apiHelper.getTopStories(count);

    suspend fun login(username: String, password: String) =  apiHelper.login(username, password)
}