package com.play.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.play.PlayApplication
import com.play.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(
            findViewById<View>(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        )
        val sbView = snackbar.view
        val textView = sbView
            .findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(PlayApplication.applicationContext(), android.R.color.white))
        snackbar.show()
    }

    fun changeTheme(theme: String?) {
        if (theme != null && theme == "ThemeYellow") {
            setTheme(R.style.ThemeYellow)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

}