package self.mvvmexample.koin


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import self.mvvmexample.utils.ConnectionManager
import self.mvvmexample.utils.Prefs

object AppModule {


    // Koin used instead of Dagger2 for dependency Injection
    fun getModule(): Module = applicationContext {

        bean {
            GsonBuilder().serializeNulls().create() as Gson
        }

        bean {
            JsonParser()
        }

        bean {
            Prefs(get(), get())
        }

        bean {
            ConnectionManager(get())
        }

    }
}