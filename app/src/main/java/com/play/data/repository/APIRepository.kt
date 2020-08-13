package com.play.data.repository

import com.play.data.api.ApiHelper
import com.play.data.perfs.PreferencesHelper
import javax.inject.Inject

class APIRepository @Inject constructor(private val apiHelper: ApiHelper,
                                        private val preferencesHelper: PreferencesHelper) {

    suspend fun getTopStories() =  apiHelper.getTopStories()

    suspend fun getTopStories(count: Int) =  apiHelper.getTopStories(count);

    suspend fun login(username: String, password: String) =  apiHelper.login(username, password)

    fun setTheme(theme: String) =  preferencesHelper.setTheme(theme);

    fun getTheme() : String = preferencesHelper.getTheme()!!;
}
