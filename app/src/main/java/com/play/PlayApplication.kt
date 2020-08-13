package com.play

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlayApplication : Application(){

    init {
        instance = this
    }

    companion object {
        private var instance: PlayApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


}
