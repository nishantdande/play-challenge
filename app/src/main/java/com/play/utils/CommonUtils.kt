package com.play.utils

import com.play.PlayApplication
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class CommonUtils {

    companion object {
        @Throws(IOException::class)
        fun readJSON(filename: String?): String? {
            // Opening data.json file
            val inputStream: InputStream? =
                filename?.let { PlayApplication.applicationContext().assets.open(it) }
            val r =
                BufferedReader(InputStreamReader(inputStream))
            val responseStringBuilder = StringBuilder()
            var line: String?
            while (r.readLine().also { line = it } != null) {
                responseStringBuilder.append(line).append('\n')
            }
            return responseStringBuilder.toString()
        }
    }
}