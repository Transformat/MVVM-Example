package bridgingtech.denefits.modules.baseModule

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import self.mvvmexample.utils.MyApplication
import kotlin.reflect.KClass

abstract class BaseViewModel : ViewModel() {

    //Generic Live data to post UI Events

    var loader = MutableLiveData<Boolean>()
    var showError = MutableLiveData<String>()
    var showSnackBarFieldError = MutableLiveData<Pair<String, Int>>()
    var showSnackbarGeneric = MutableLiveData<String>()
    var activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()
    var resources = MyApplication.appInstance?.resources!!
    var currencySymbol = "$"
    var onBackPressed = MutableLiveData<Boolean>()


}