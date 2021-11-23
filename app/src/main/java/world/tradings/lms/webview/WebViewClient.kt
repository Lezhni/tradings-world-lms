package world.tradings.lms.webview

import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebViewClient(private val progressBar: ProgressBar) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        progressBar.visibility = View.VISIBLE
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        progressBar.visibility = View.GONE
        view?.visibility = View.VISIBLE
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (view == null || request == null) {
            return false
        }

        val url = request.url.toString()
        if (url.contains("https://my.tradings.world")) {
            return false
        }

        val intent = Intent(Intent.ACTION_VIEW, request.url);
        view.context.startActivity(intent)

        return true
    }
}