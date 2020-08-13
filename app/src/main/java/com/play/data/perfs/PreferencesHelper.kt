package com.play.data.perfs

import com.play.data.model.Story

interface PreferencesHelper {

    fun setTheme(theme: String?)

    fun getTheme(): String?

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun setStory(story: Story)

    fun getStory(): Story?

    fun clearStory()
}