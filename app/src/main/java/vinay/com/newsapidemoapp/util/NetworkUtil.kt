package vinay.com.newsapidemoapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil {
    companion object {
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }
    }
}