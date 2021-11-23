package world.tradings.lms.services

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import world.tradings.lms.utils.NetworkUtils
import world.tradings.lms.webview.DownloadListener
import world.tradings.lms.webview.WebAppInterface
import world.tradings.lms.webview.WebViewClient

class BrowserService(
    private val context: Context,
    private val browser: WebView,
    private val progressBar: ProgressBar,
    private val noNetworkMsg: TextView,
    private val checkNetworkBtn: Button,
) {

    public fun init() {
        checkNetworkBtn.setOnClickListener { start() }
        prepare()
        start()
    }

    fun resume() {
        if (!NetworkUtils.hasInternetConnection(context)) {
            showConnectionError()
            return
        }

        if (browser.visibility == View.GONE) {
            loadSite()
            hideConnectionError()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun prepare() {
        browser.settings.javaScriptEnabled = true
        browser.settings.domStorageEnabled = true
        browser.settings.databaseEnabled = true
        browser.settings.allowFileAccess = true
        browser.settings.builtInZoomControls = true
        browser.settings.displayZoomControls = false;

        browser.webViewClient = WebViewClient(progressBar)
        browser.setDownloadListener(DownloadListener(context))
        browser.addJavascriptInterface(WebAppInterface(context), "Android")
    }

    private fun start() {
        if (!NetworkUtils.hasInternetConnection(context)) {
            showConnectionError()
            return
        }

        loadSite()
        hideConnectionError()
    }

    private fun loadSite() {
        if (browser.url != null) {
            browser.reload()
            return
        }

        browser.loadUrl("https://my.tradings.world")
    }

    private fun showConnectionError() {
        progressBar.visibility = View.GONE
        browser.visibility = View.GONE
        noNetworkMsg.visibility = View.VISIBLE
        checkNetworkBtn.visibility = View.VISIBLE
    }

    private fun hideConnectionError() {
        noNetworkMsg.visibility = View.GONE
        checkNetworkBtn.visibility = View.GONE
    }
}