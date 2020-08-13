package com.mindorks.framework.mvvm.utils

import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.play.PlayApplication

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error(msg: Int, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, PlayApplication.applicationContext().getString(msg))
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

}