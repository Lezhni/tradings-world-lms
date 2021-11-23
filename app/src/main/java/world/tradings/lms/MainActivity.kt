package world.tradings.lms

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import world.tradings.lms.services.BrowserService

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var noNetworkMsg: TextView
    private lateinit var checkNetworkBtn: Button
    private lateinit var browserService: BrowserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        progressBar = findViewById(R.id.progress_bar)
        noNetworkMsg = findViewById(R.id.no_network_msg)
        checkNetworkBtn = findViewById(R.id.check_network_btn)

        browserService = BrowserService(
            this,
            webView,
            progressBar,
            noNetworkMsg,
            checkNetworkBtn,
        )
        browserService.init()
    }

    override fun onResume() {
        super.onResume()
        browserService.resume()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }
}