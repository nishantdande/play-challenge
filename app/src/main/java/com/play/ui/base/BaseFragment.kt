package com.play.ui.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.play.PlayApplication
import com.play.utils.CommonUtils

open class BaseFragment : Fragment() {

    private var mProgressDialog: ProgressDialog? = null

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

    open fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this.context)
    }

    open fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.cancel()
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}