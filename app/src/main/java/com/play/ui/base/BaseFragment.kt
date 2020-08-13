package com.play.ui.base

import android.R
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.play.PlayApplication

open class BaseFragment : Fragment() {


    fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(
            requireActivity().findViewById<View>(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        )
        val sbView = snackbar.view
        val textView = sbView
            .findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(PlayApplication.applicationContext(), android.R.color.white))
        snackbar.show()
    }
}