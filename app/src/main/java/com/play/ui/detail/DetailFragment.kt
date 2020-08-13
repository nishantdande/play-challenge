package com.play.ui.detail

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.play.R
import com.play.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlin.reflect.KProperty

class DetailFragment : BaseFragment() {

    private var dashboardViewModel : DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeBtn.setOnClickListener(View.OnClickListener { close() });
        dashboardViewModel.getStory.observe(viewLifecycleOwner, Observer {
            title.text = it.data?.title
            it.data?.url?.let { it1 -> loadUrl(it1) }
        })

    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.getStory();
    }

    fun close() {
        dashboardViewModel.clearStory()
        requireActivity().feed_view_pager.currentItem = 1
    }

    private fun loadUrl(url: String) {
        mWebview.getSettings().setJavaScriptEnabled(true) // enable javascript
        mWebview.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                showSnackBar(description)
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }
        })
        mWebview.loadUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dashboardViewModel.clearStory();
    }
}

private operator fun Any.setValue(
    detailFragment: DetailFragment,
    property: KProperty<*>,
    detailViewModel: DetailViewModel
) {
    TODO("Not yet implemented")
}
