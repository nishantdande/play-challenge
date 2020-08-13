package com.play.data.perfs

import android.content.Context
import android.content.SharedPreferences
import com.play.PlayApplication
import com.play.data.api.ApiService
import com.play.data.model.Story
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(private val apiService: ApiService): PreferencesHelper {

    private val PREF_KEY_THEME = "PREF_KEY_THEME"
    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    private val PREF_KEY_STORY = "PREF_KEY_STORY"
    private var mPrefs: SharedPreferences? = null

    init {
        mPrefs = PlayApplication.applicationContext()
            .getSharedPreferences("android-play", Context.MODE_PRIVATE)
    }

    override fun setTheme(theme: String?) {
        mPrefs!!.edit().putString(PREF_KEY_THEME, theme)
            .apply()
    }

    override fun getTheme(): String? {
        return mPrefs!!.getString(PREF_KEY_THEME, "AppTheme")
    }

    override fun getAccessToken(): String? {
        TODO("Not yet implemented")
    }

    override fun setAccessToken(accessToken: String?) {
        TODO("Not yet implemented")
    }

    override fun setStory(story: Story?) {
        TODO("Not yet implemented")
    }

    override fun getStory(): Story? {
        TODO("Not yet implemented")
    }

    override fun clearStory() {
        TODO("Not yet implemented")
    }


}