package world.tradings.lms

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class CustomWebViewClient(private val progressBar: ProgressBar) : WebViewClient() {

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
        progressBar.visibility = View.VISIBLE
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if (!loadFailed) {
            progressBar.visibility = View.GONE
            view?.visibility = View.VISIBLE
        }
    }
}
