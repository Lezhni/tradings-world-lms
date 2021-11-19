package world.tradings.lms

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import world.tradings.lms.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    private lateinit var browser: WebView
    private lateinit var noNetworkMsg: TextView
    private lateinit var checkNetworkBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        browser = findViewById(R.id.webview)
        noNetworkMsg = findViewById(R.id.no_network_msg)
        checkNetworkBtn = findViewById(R.id.check_network_btn)

        checkNetworkBtn.setOnClickListener {
            checkNetworkStatus(true)
        }

        prepareBrowser()

        checkNetworkStatus()

        loadSite()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun prepareBrowser() {
        browser.settings.javaScriptEnabled = true
        browser.settings.domStorageEnabled = true
        browser.addJavascriptInterface(WebAppInterface(this), "Android")
        browser.webViewClient = CustomWebViewClient()
    }

    private fun checkNetworkStatus(updateSite: Boolean = false) {
        val internetEnabled: Boolean = NetworkUtils.internetEnabled(this)
        if (!internetEnabled) {
            browser.visibility = View.GONE
            noNetworkMsg.visibility = View.VISIBLE
            checkNetworkBtn.visibility = View.VISIBLE
            return
        }

        if (updateSite) {
            loadSite()
            noNetworkMsg.visibility = View.GONE
            checkNetworkBtn.visibility = View.GONE
        }
    }

    private fun loadSite() {
        browser.loadUrl("https://my.tradings.world")
    }
}