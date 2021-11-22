package world.tradings.lms

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import world.tradings.lms.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    private lateinit var browser: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var noNetworkMsg: TextView
    private lateinit var checkNetworkBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        browser = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progress_bar)
        noNetworkMsg = findViewById(R.id.no_network_msg)
        checkNetworkBtn = findViewById(R.id.check_network_btn)

        checkNetworkBtn.setOnClickListener {
            startBrowser()
        }

        prepareBrowser()
        startBrowser(true)
    }

    override fun onResume() {
        super.onResume()
        startBrowser()
    }

    override fun onBackPressed() {
        if (browser.canGoBack()) browser.goBack() else super.onBackPressed()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun prepareBrowser() {
        browser.settings.javaScriptEnabled = true
        browser.settings.domStorageEnabled = true
        browser.settings.databaseEnabled = true
        browser.settings.allowFileAccess = true
        browser.addJavascriptInterface(WebAppInterface(this), "Android")
        browser.webViewClient = CustomWebViewClient(progressBar)
    }

    private fun startBrowser(loadIndexPage: Boolean = false) {
        if (!NetworkUtils.hasInternetConnection(this)) {
            showConnectionError()
            return
        }

        loadSite(loadIndexPage)
        hideConnectionError()
    }

    private fun loadSite(loadIndexPage: Boolean) {
        if (loadIndexPage) browser.loadUrl("https://my.tradings.world") else browser.reload()
    }

    private fun showConnectionError() {
        browser.visibility = View.GONE
        noNetworkMsg.visibility = View.VISIBLE
        checkNetworkBtn.visibility = View.VISIBLE
    }

    private fun hideConnectionError() {
        noNetworkMsg.visibility = View.GONE
        checkNetworkBtn.visibility = View.GONE
    }
}