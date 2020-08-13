package com.play.module

import android.net.Uri
import android.util.Log
import com.play.utils.CommonUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.util.*

class FakeInterceptor : Interceptor{

    private val mContentType = "application/json"
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("FakeInterceptor", "FakeInterceptor");
        var response: Response? = null
        val uri = Uri.parse(chain.request().url.toUri().toString())

        if (Objects.requireNonNull(uri.path)!!.contains("login")) {
            var responseFileName: String? = null
            if (uri.getQueryParameter("username") == "test@worldofplay.in" && uri.getQueryParameter(
                    "password"
                ) == "Worldofplay@2020"
            ) {
                responseFileName = "success/login.json"
                val responseMessage: String? = CommonUtils.readJSON(responseFileName)
                response = responseMessage?.let {
                    Response.Builder()
                        .code(200)
                        .message(it)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_2)
                        .body(
                            ResponseBody.create(
                                mContentType.toMediaTypeOrNull(),
                                responseMessage.toByteArray()
                            )
                        )
                        .addHeader("content-type", mContentType)
                        .build()
                }
            } else {
                responseFileName = "error/incorrect_credential.json"
                val responseMessage: String? = CommonUtils.readJSON(responseFileName)
                throw IOException(responseMessage)
            }
        } else {
            return chain.proceed(chain.request())
        }

        return response!!
    }

}