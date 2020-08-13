package com.play.utils

import com.play.PlayApplication
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.regex.Pattern

class CommonUtils {
    companion object {

        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")


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

        fun isEmailValid(email: String?): Boolean {
            val pattern =
                Pattern.compile(EMAIL_PATTERN)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
    }
}