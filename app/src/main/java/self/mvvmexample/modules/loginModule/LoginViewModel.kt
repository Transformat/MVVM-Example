package self.mvvmexample.modules.loginModule

import android.text.TextUtils
import bridgingtech.denefits.modules.baseModule.BaseViewModel
import self.mvvmexample.R
import self.mvvmexample.utils.Utility

class LoginViewModel : BaseViewModel() {

    //Fields bound directly to UI
    var email = ""
    var password = ""

    enum class LoginFields(var value: Int) {
        EMAIL(0),
        PASSWORD(1);
    }


    //Validations on login pressed
    fun login() {

        if (!Utility.isEmailValid(email.toString().trim())) {
            showSnackBarFieldError.postValue(Pair(resources.getString(R.string.error_email_user_empty), LoginFields.EMAIL.value))
            return
        }
        if (TextUtils.isEmpty(password.toString())) {
            showSnackBarFieldError.postValue(Pair(resources.getString(R.string.error_enter_password), LoginFields.PASSWORD.value))
            return
        }
        if (TextUtils.isEmpty(password.toString())) {
            showSnackBarFieldError.postValue(Pair(resources.getString(R.string.error_password_spaces), LoginFields.PASSWORD.value))
            return
        }
        if (password.toString().length < 6) {
            showSnackBarFieldError.postValue(Pair(resources.getString(R.string.error_password_length), LoginFields.PASSWORD.value))
            return
        }

        //Showing dummy loader
        loader.postValue(true)
        android.os.Handler().postDelayed({
            loader.postValue(false)
        }, 3000)
    }
}