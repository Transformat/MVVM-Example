package self.mvvmexample.utils

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import self.mvvmexample.koin.AppModule
import timber.log.Timber


class MyApplication : Application() {


    val gson: Gson by inject()

    val jsonParser: JsonParser by inject()

    val prefs: Prefs by inject()

    val connectionManager: ConnectionManager by inject()

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        MultiDex.install(this)
        //Dependency Injection Using Koin
        startKoin(this, listOf(AppModule.getModule()))
        Timber.plant(Timber.DebugTree())
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var appInstance: MyApplication? = null
    }


}


