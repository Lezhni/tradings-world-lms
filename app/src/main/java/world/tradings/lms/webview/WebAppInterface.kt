package world.tradings.lms.webview

import android.content.Context
import android.webkit.JavascriptInterface

class WebAppInterface(private val context: Context) {

    @JavascriptInterface
    fun showNotification(text: String) {
    }
}
