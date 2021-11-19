package world.tradings.lms.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtils {

    companion object {

        fun internetEnabled(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capability = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                return capability.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                val networks = cm.allNetworks
                networks.forEach {
                    val networkInfo = cm.getNetworkInfo(it)
                    if (networkInfo != null && networkInfo.isConnected) {
                        return true
                    }
                }
            }

            return false
        }
    }
}