package world.tradings.lms

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient() : WebViewClient() {

    private var loadFailed: Boolean = false

    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?,
    ) {
        loadFailed = true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        loadFailed = false
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if (!loadFailed) {
            view?.visibility = View.VISIBLE
        }
    }
}