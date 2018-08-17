package self.mvvmexample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionManager(var con: Context) {
    internal var connectManager: ConnectivityManager? = null
    internal var wifiInfo: NetworkInfo? = null
    internal var mobileInfo: NetworkInfo? = null
    internal var connected = false

    fun isOnline(): Boolean {
        try {
            connectManager = con
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectManager?.activeNetworkInfo
            connected = (networkInfo != null && networkInfo.isAvailable
                    && networkInfo.isConnected)
            return connected

        } catch (e: Exception) {
            println("CheckConnectivity Exception: " + e.message)
        }

        return connected
    }


}
